package com.openwebinars.spring.controladores;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openwebinars.spring.entidades.Carta;
import com.openwebinars.spring.entidades.LineaPedidos;
import com.openwebinars.spring.entidades.Pedidos;
import com.openwebinars.spring.entidades.Usuario;
import com.openwebinars.spring.entidades.Pedidos.EstadoPedido;
import com.openwebinars.spring.servicios.CartaServicio;
import com.openwebinars.spring.servicios.LineaPedidosServicio;
import com.openwebinars.spring.servicios.PedidosServicio;
import com.openwebinars.spring.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class WebControlador {

	@Autowired
	private CartaServicio bdCarta;

	@Autowired
	private UsuarioServicio bdUsuario;

	@Autowired
	private LineaPedidosServicio bdlineaPedidos;

	@Autowired
	private PedidosServicio bdPedidos;

	@Autowired
	private HttpSession sesion;

	@GetMapping("/")
	public String index(Model model, HttpServletRequest request) {

		List<Carta> cartas = bdCarta.findAll();

		model.addAttribute("currentUri", request.getRequestURI());

		model.addAttribute("cartas", cartas);
		model.addAttribute("usuarioLogueado",
				(sesion.getAttribute("usuarioLogueado") != null) ? sesion.getAttribute("usuarioLogueado") : false);
		model.addAttribute("usuarioAdmin",
				(sesion.getAttribute("usuarioAdmin") != null) ? sesion.getAttribute("usuarioAdmin") : false);
		boolean log = (boolean) model.getAttribute("usuarioLogueado");

		if (log) {
			Usuario u = bdUsuario.findById((Long) sesion.getAttribute("id"));

			if (bdPedidos.findByUsuarioId((Long) sesion.getAttribute("id")) != null) {

				Pedidos p = bdPedidos.findByEstado(EstadoPedido.EN_PROCESO, (Long) sesion.getAttribute("id"));
				Integer contadorPedidos = bdPedidos.countPedidos(u, p);

				model.addAttribute("contador_carrito", contadorPedidos);
				sesion.setAttribute("contador_carrito", contadorPedidos);
				sesion.setAttribute("IdPedido", p.getId());
			} else {
				model.addAttribute("contador_carrito", 0);
				sesion.setAttribute("contador_carrito", 0);

			}

		}

		return "public/index";
	}

	@GetMapping("/login")
	public String login(Model model, @RequestParam(value = "error", required = false) String error) {

		if (error != null) {
			model.addAttribute("error", "Usuario o contraseña incorrectos. Inténtalo de nuevo.");
		} else {
			model.addAttribute("error", null);
		}
		return "public/login";
	}

	@GetMapping("/registro")
	public String registro(Model model, @RequestParam(value = "error", required = false) String error) {

		model.addAttribute("usuarioNuevo", new Usuario());

		if (error != null) {
			model.addAttribute("error", "Confirma la contraseña correctamente.");
		} else {
			model.addAttribute("error", null);
		}

		return "public/registro";
	}

	@PostMapping("/registro/submit")
	public String nuevoUsuarioSubmit(@ModelAttribute("usuarioNuevo") Usuario u) {

		bdUsuario.add(u);

		return "redirect:/login";
	}

	@GetMapping("/perfil")
	public String getMethodName(Model model) {
		Usuario u = bdUsuario.findById((Long) sesion.getAttribute("id"));
		model.addAttribute("usuario", u);
		return "public/perfil";
	}

	@PostMapping("/perfil/edit")
	public String postMethodName(@ModelAttribute("nombre") String nombre, @ModelAttribute("apellido") String apellido,
			@ModelAttribute("email") String email, @ModelAttribute("dni") String dni,
			@ModelAttribute("contraseña") String contraseña,
			@ModelAttribute("direccion") String direccion, @ModelAttribute("telefono") String telefono,
			BCryptPasswordEncoder passwordEncoder) {

		Usuario u = bdUsuario.findById((Long) sesion.getAttribute("id"));

		u.setNombre(nombre);
		u.setApellido(apellido);
		u.setEmail(email);
		u.setDni(dni);
		if (contraseña != null) {
			contraseña = passwordEncoder.encode(contraseña);
			u.setContraseña(contraseña);
		}
		u.setDireccion(direccion);
		u.setTelefono(telefono);

		bdUsuario.edit(u);

		return "redirect:/";
	}

	@GetMapping("/carrito")
	public String carrito(Model model) {

		Long id = (Long) sesion.getAttribute("IdPedido");

		if (id != null) {
			Pedidos p = bdPedidos.findById(id);
			List<LineaPedidos> listaLineaPedidos = bdlineaPedidos.findByPedidoId(id);

			model.addAttribute("pedido", p);
			model.addAttribute("listaLineaPedidos", listaLineaPedidos);
		} else {
			model.addAttribute("pedido", null);
			model.addAttribute("listaLineaPedidos", null);
		}

		return "public/carrito";
	}

	@PostMapping("/carrito/add")
	public String carritoAdd(Model model, @RequestParam(value = "idCarta") Long IdCarta,
			@RequestParam(value = "cantidad") Integer cantidad) {

		Carta carta = bdCarta.findById(IdCarta);
		Usuario u = bdUsuario.findById((Long) sesion.getAttribute("id"));

		Pedidos p;
		if (bdPedidos.findByEstado(EstadoPedido.EN_PROCESO, u.getId()) != null) {
			p = bdPedidos.findByEstado(EstadoPedido.EN_PROCESO, u.getId());
		} else {
			p = new Pedidos(LocalDateTime.now(), 0, u.getDireccion(), EstadoPedido.EN_PROCESO, u);
			bdPedidos.add(p);
		}

		LineaPedidos existegLinea = bdlineaPedidos.findByCartaId(carta.getId());

		if (existegLinea != null) {
			existegLinea.setCantidad(existegLinea.getCantidad() + cantidad);
			existegLinea.setPrecioTotalLinea(existegLinea.getPrecioTotalLinea() + (carta.getPrecioCarta() * cantidad));
			bdlineaPedidos.add(existegLinea);
		} else {
			LineaPedidos linea = new LineaPedidos(cantidad, (carta.getPrecioCarta() * cantidad), p, carta);
			bdlineaPedidos.add(linea);
		}

		p.setPrecioTotal(p.getPrecioTotal() + (carta.getPrecioCarta() * cantidad));
		bdPedidos.edit(p);

		if (p.getId() != null) {
			sesion.setAttribute("IdPedido", p.getId());
		}

		if (bdPedidos.findCarta(IdCarta, u.getId()) != null) {
			Integer contadorCarrito = (Integer) sesion.getAttribute("contador_carrito");

			sesion.setAttribute("contador_carrito", contadorCarrito + 1);
			model.addAttribute("contador_carrito", contadorCarrito + 1);
		}
		return "redirect:/";
	}
}
