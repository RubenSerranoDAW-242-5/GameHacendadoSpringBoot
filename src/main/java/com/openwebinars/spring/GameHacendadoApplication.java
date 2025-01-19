package com.openwebinars.spring;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.openwebinars.spring.entidades.Carta;
import com.openwebinars.spring.entidades.Categorias;
import com.openwebinars.spring.entidades.Usuario;
import com.openwebinars.spring.repositorio.CartaRepositorio;
import com.openwebinars.spring.repositorio.CategoriaRepositorio;
import com.openwebinars.spring.repositorio.UsuarioRepositorio;

@SpringBootApplication
public class GameHacendadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameHacendadoApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(UsuarioRepositorio usuarioRepositorio, CartaRepositorio cartaRepositorio,
			CategoriaRepositorio categoriaRepositorio, BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			if (usuarioRepositorio.count() == 0 && categoriaRepositorio.count() == 0 && cartaRepositorio.count() == 0) {

				usuarioRepositorio.save(new Usuario("Juan", "Pérez", "juan.perez@example.com", "12345678A",
						passwordEncoder.encode("123"), "admin",
						"Calle Falsa 123, Madrid", "+34 600 123 456"));
				usuarioRepositorio.save(new Usuario("Ana", "García", "ana.garcia@example.com", "87654321B",
						passwordEncoder.encode("123"),
						"user", "Av. Siempreviva 456, Barcelona", "+34 600 654 321"));

				Carta carta1 = new Carta("Buu, Unlimited Majin", "Battle Card", "8", "Azul", "BT25-146",
						187, "buuazul.png", 10);
				Carta carta2 = new Carta("Android 21, Transcendental Predator", "Battle Card", "4", "Azul/Verde",
						"BT20-149", 159, "21.png", 10);
				Carta carta3 = new Carta("SS4: The Vermilion Saiyans", "Extra Card", "1", "Negro", "BT15-152",
						101, "vermilion.png", 10);
				Carta carta4 = new Carta("Vegito, Warrior From Another Dimension", "Unison Card", "X", "Negro",
						"BT11-154", 199, "vegitounison.png", 10);
				Carta carta5 = new Carta("Power of Potara - Vegito, Kefla & Zamasu", "Battle Card", "8",
						"Azul/Amarillo", "BT7-131", 142, "vegitobi.png", 10);
				Carta carta6 = new Carta("Vegeta, Awakened Feelings", "Leader Card", "", "Rojo", "BT24-001",
						72, "vegetarojolider.png", 10);
				Carta carta7 = new Carta("Perfected Ultra Instinct Son Goku, Transcendence", "Battle Card", "10",
						"Negro", "BT26-140", 146, "ultradistintonegro.png", 10);
				Carta carta8 = new Carta("SS Trunks, Tournament Battle to the Death", "Leader Card", "", "Verde",
						"BT25-070", 186, "trankaslider.png", 10);
				Carta carta9 = new Carta("Tapion, Hero Revived in the Present", "Leader Card", "", "Azul", "BT24-025",
						138, "tapionetalider.png", 10);
				Carta carta10 = new Carta("SS Son Goku, Beginning of a Legend", "Leader Card", "", "Verde", "BT24-055",
						134, "superkokunlider.png", 10);
				Carta carta11 = new Carta("King Piccolo, Final Stage of Conquest", "Leader Card", "", "Rojo",
						"BT25-002", 195, "piccolodaimaolider.png", 10);
				Carta carta12 = new Carta("Super Mira, Diabolical Fusion", "Unison Card", "X", "Amarillo", "BT16-002",
						54, "miraunison.png", 10);
				Carta carta13 = new Carta("SS4 Vegito, Sparking Potara Warrior", "Leader Card", "", "Negro", "BT24-112",
						70, "lidervegito4.png", 10);
				Carta carta14 = new Carta("Cell Xeno, Unspeakable Abomination", "Battle Card", "12", "Amarillo/Verde",
						"BT9-137", 198, "kuka.png", 10);
				Carta carta15 = new Carta("Son Goku, Face-Off With the Great Demon king", "Leader Card", "", "Rojo",
						"BT25-001", 96, "kokunkidlider.png", 10);
				Carta carta16 = new Carta("Son Goku, Apex of the Origin", "Battle Card", "7", "Negro", "BT25-148",
						85, "kokunfrezernegro.png", 10);
				Carta carta17 = new Carta("Son Goku, Fist of Fate", "Battle Card", "8", "Rojo", "BT25-145",
						162, "kidkurojo.png", 10);
				Carta carta18 = new Carta("Supreme Kai of Time, Brainwashed", "Battle Card", "8", "Negro", "BT16-149",
						175, "kailavado.png", 10);
				Carta carta19 = new Carta("Heroines' Lineage", "Extra Card", "1", "Negro", "EB1-68",
						132, "Heroines.png", 10);
				Carta carta20 = new Carta("SS3 Gohanks, Interdimensional Warrior", "Unison Card", "X", "Rojo",
						"BT13-153",
						184, "gohanksunison.png", 10);
				Carta carta21 = new Carta("Bursting Rage", "Extra Card", "0", "Rojo", "BT22-138",
						113, "gohanextra.png", 10);
				Carta carta22 = new Carta("SS4 Gogeta, Strongest Fusion Explosion", "Leader Card", "", "Amarillo",
						"BT25-098",
						171, "gogetalider.png", 10);
				Carta carta23 = new Carta("Frieza, Scourge of Saiyans", "Leader Card", "", "Verde", "BT24-056",
						117, "freezerlider.png", 10);
				Carta carta24 = new Carta("Metamorphic Android Cell", "Battle Card", "10", "Verde", "BT26-139",
						193, "cellverde.png", 10);
				Carta carta25 = new Carta("Majin Buu, Shape-Shifter", "Leader Card", "", "Azul", "BT25-037",
						164, "buulider.png", 10);
				Carta carta26 = new Carta("Boujack, Crashing the Tournament", "Leader Card", "", "Verde", "BT25-071",
						89, "bojaklider.png", 10);
				Carta carta27 = new Carta("Son Gohan, Beyond the Ultimate", "Battle Card", "8", "Azul", "BT19-152",
						63, "bestia.png", 10);
				Carta carta28 = new Carta("SS Gogeta, Fusion Reborn", "Battle Card", "8", "Azul/Amarillo", "BT22-140",
						103, "gogetabi.png", 10);
				Carta carta29 = new Carta("SSB Gogeta, Shining Blue Strongest Warrior", "Battle Card", "8", "Azul",
						"BT26-138",
						156, "gogetablue.png", 10);
				Carta carta30 = new Carta("SS4 Gogeta, Unrivaled Sparking", "Battle Card", "8", "Amarillo", "BT25-147",
						53, "gogetass4ama.png", 10);
				Carta carta31 = new Carta("Ultra Instinct Son Goku, State of the Gods", "Battle Card", "8", "Amarillo",
						"BT23-140",
						90, "ultradistinto.png", 10);
				Carta carta32 = new Carta("SSB Kaio-Ken Vegito, Blue Potara-Fusion Warrior Champion", "Battle Card",
						"8", "Negro", "BT24-139", 130, "vegitobluexeno.png", 10);
				Carta carta33 = new Carta("SS4 Vegito, A Light in the Dark", "Battle Card", "8", "Roja", "BT18-139",
						48, "vegitoss4rojo.png", 10);

				cartaRepositorio.save(carta1);
				cartaRepositorio.save(carta2);
				cartaRepositorio.save(carta3);
				cartaRepositorio.save(carta4);
				cartaRepositorio.save(carta5);
				cartaRepositorio.save(carta6);
				cartaRepositorio.save(carta7);
				cartaRepositorio.save(carta8);
				cartaRepositorio.save(carta9);
				cartaRepositorio.save(carta10);
				cartaRepositorio.save(carta11);
				cartaRepositorio.save(carta12);
				cartaRepositorio.save(carta13);
				cartaRepositorio.save(carta14);
				cartaRepositorio.save(carta15);
				cartaRepositorio.save(carta16);
				cartaRepositorio.save(carta17);
				cartaRepositorio.save(carta18);
				cartaRepositorio.save(carta19);
				cartaRepositorio.save(carta20);
				cartaRepositorio.save(carta21);
				cartaRepositorio.save(carta22);
				cartaRepositorio.save(carta23);
				cartaRepositorio.save(carta24);
				cartaRepositorio.save(carta25);
				cartaRepositorio.save(carta26);
				cartaRepositorio.save(carta27);
				cartaRepositorio.save(carta28);
				cartaRepositorio.save(carta29);
				cartaRepositorio.save(carta30);
				cartaRepositorio.save(carta31);
				cartaRepositorio.save(carta32);
				cartaRepositorio.save(carta33);

				Categorias saiyan = new Categorias("Saiyan", new HashSet<>());
				Categorias gogeta = new Categorias("Gogeta", new HashSet<>());
				Categorias vegito = new Categorias("Vegito", new HashSet<>());
				Categorias majin = new Categorias("Majin", new HashSet<>());
				Categorias dualAttack = new Categorias("Dual Attack", new HashSet<>());
				Categorias quadrupleStrike = new Categorias("Quadruple Strike", new HashSet<>());
				Categorias barrier = new Categorias("Barrier", new HashSet<>());
				Categorias victoryStrike = new Categorias("Victory Strike", new HashSet<>());
				Categorias earthling = new Categorias("Earthling", new HashSet<>());
				Categorias blocker = new Categorias("Blocker", new HashSet<>());
				Categorias deflect = new Categorias("Deflect", new HashSet<>());
				Categorias energyExhaust = new Categorias("Energy-Exhaust", new HashSet<>());
				Categorias doubleStrike = new Categorias("Double Strike", new HashSet<>());
				Categorias tripleStrike = new Categorias("Triple Strike", new HashSet<>());
				Categorias activate = new Categorias("Activate", new HashSet<>());
				Categorias permanent = new Categorias("Permanent", new HashSet<>());

				categoriaRepositorio.save(saiyan);
				categoriaRepositorio.save(gogeta);
				categoriaRepositorio.save(vegito);
				categoriaRepositorio.save(majin);
				categoriaRepositorio.save(dualAttack);
				categoriaRepositorio.save(quadrupleStrike);
				categoriaRepositorio.save(barrier);
				categoriaRepositorio.save(victoryStrike);
				categoriaRepositorio.save(earthling);
				categoriaRepositorio.save(blocker);
				categoriaRepositorio.save(deflect);
				categoriaRepositorio.save(energyExhaust);
				categoriaRepositorio.save(doubleStrike);
				categoriaRepositorio.save(tripleStrike);
				categoriaRepositorio.save(activate);
				categoriaRepositorio.save(permanent);

				carta1.getCategorias().add(majin);
				carta1.getCategorias().add(dualAttack);
				carta1.getCategorias().add(activate);
				cartaRepositorio.save(carta1);

				carta2.getCategorias().add(dualAttack);
				carta2.getCategorias().add(barrier);
				carta2.getCategorias().add(blocker);
				carta2.getCategorias().add(deflect);
				carta2.getCategorias().add(energyExhaust);
				carta2.getCategorias().add(permanent);
				cartaRepositorio.save(carta2);

				carta3.getCategorias().add(saiyan);
				carta3.getCategorias().add(barrier);
				carta3.getCategorias().add(activate);
				cartaRepositorio.save(carta3);

				carta4.getCategorias().add(saiyan);
				carta4.getCategorias().add(vegito);
				carta4.getCategorias().add(doubleStrike);
				carta4.getCategorias().add(activate);
				carta4.getCategorias().add(permanent);
				cartaRepositorio.save(carta4);

				carta5.getCategorias().add(saiyan);
				carta5.getCategorias().add(vegito);
				carta5.getCategorias().add(energyExhaust);
				carta5.getCategorias().add(tripleStrike);
				cartaRepositorio.save(carta5);

				carta6.getCategorias().add(saiyan);
				carta6.getCategorias().add(activate);
				cartaRepositorio.save(carta6);

				carta7.getCategorias().add(saiyan);
				carta7.getCategorias().add(victoryStrike);
				carta7.getCategorias().add(activate);
				cartaRepositorio.save(carta7);

				carta8.getCategorias().add(saiyan);
				carta8.getCategorias().add(activate);
				cartaRepositorio.save(carta8);

				carta9.getCategorias().add(activate);
				cartaRepositorio.save(carta9);

				carta10.getCategorias().add(saiyan);
				carta10.getCategorias().add(activate);
				cartaRepositorio.save(carta10);

				carta11.getCategorias().add(activate);
				cartaRepositorio.save(carta11);

				carta12.getCategorias().add(barrier);
				carta12.getCategorias().add(blocker);
				carta12.getCategorias().add(dualAttack);
				carta12.getCategorias().add(activate);
				cartaRepositorio.save(carta12);

				carta13.getCategorias().add(saiyan);
				carta13.getCategorias().add(vegito);
				carta13.getCategorias().add(activate);
				carta13.getCategorias().add(permanent);
				cartaRepositorio.save(carta13);

				carta14.getCategorias().add(dualAttack);
				carta14.getCategorias().add(quadrupleStrike);
				carta14.getCategorias().add(barrier);
				carta14.getCategorias().add(deflect);
				carta14.getCategorias().add(energyExhaust);
				cartaRepositorio.save(carta14);

				carta15.getCategorias().add(saiyan);
				carta15.getCategorias().add(activate);
				cartaRepositorio.save(carta15);

				carta16.getCategorias().add(saiyan);
				carta16.getCategorias().add(deflect);
				carta16.getCategorias().add(permanent);
				cartaRepositorio.save(carta16);

				carta17.getCategorias().add(saiyan);
				carta17.getCategorias().add(tripleStrike);
				carta17.getCategorias().add(deflect);
				carta17.getCategorias().add(activate);
				cartaRepositorio.save(carta17);

				carta18.getCategorias().add(doubleStrike);
				carta18.getCategorias().add(activate);
				cartaRepositorio.save(carta18);

				carta19.getCategorias().add(activate);
				cartaRepositorio.save(carta19);

				carta20.getCategorias().add(saiyan);
				carta20.getCategorias().add(dualAttack);
				carta20.getCategorias().add(barrier);
				carta20.getCategorias().add(blocker);
				carta20.getCategorias().add(earthling);
				carta20.getCategorias().add(activate);
				cartaRepositorio.save(carta20);

				carta21.getCategorias().add(activate);
				cartaRepositorio.save(carta21);

				carta22.getCategorias().add(saiyan);
				carta22.getCategorias().add(gogeta);
				carta22.getCategorias().add(permanent);
				cartaRepositorio.save(carta22);

				carta23.getCategorias().add(activate);
				carta23.getCategorias().add(permanent);
				cartaRepositorio.save(carta23);

				carta24.getCategorias().add(activate);
				cartaRepositorio.save(carta24);

				carta25.getCategorias().add(majin);
				cartaRepositorio.save(carta25);

				carta26.getCategorias().add(activate);
				cartaRepositorio.save(carta26);

				carta27.getCategorias().add(saiyan);
				carta27.getCategorias().add(barrier);
				carta27.getCategorias().add(earthling);
				carta27.getCategorias().add(activate);
				cartaRepositorio.save(carta27);

				carta28.getCategorias().add(saiyan);
				carta28.getCategorias().add(gogeta);
				carta28.getCategorias().add(blocker);
				carta28.getCategorias().add(energyExhaust);
				carta28.getCategorias().add(permanent);
				cartaRepositorio.save(carta28);

				carta29.getCategorias().add(saiyan);
				carta29.getCategorias().add(gogeta);
				carta29.getCategorias().add(barrier);
				carta29.getCategorias().add(activate);
				cartaRepositorio.save(carta29);

				carta30.getCategorias().add(saiyan);
				carta30.getCategorias().add(gogeta);
				carta30.getCategorias().add(barrier);
				carta30.getCategorias().add(blocker);
				carta30.getCategorias().add(quadrupleStrike);
				carta30.getCategorias().add(activate);
				cartaRepositorio.save(carta30);

				carta31.getCategorias().add(saiyan);
				carta31.getCategorias().add(victoryStrike);
				carta31.getCategorias().add(permanent);
				cartaRepositorio.save(carta31);

				carta32.getCategorias().add(saiyan);
				carta32.getCategorias().add(vegito);
				carta32.getCategorias().add(deflect);
				carta32.getCategorias().add(tripleStrike);
				carta32.getCategorias().add(activate);
				carta32.getCategorias().add(permanent);
				cartaRepositorio.save(carta32);

				carta33.getCategorias().add(saiyan);
				carta33.getCategorias().add(vegito);
				carta33.getCategorias().add(quadrupleStrike);
				carta33.getCategorias().add(deflect);
				carta33.getCategorias().add(permanent);
				cartaRepositorio.save(carta33);

				saiyan.getCartas().add(carta3);
				saiyan.getCartas().add(carta4);
				saiyan.getCartas().add(carta5);
				saiyan.getCartas().add(carta6);
				saiyan.getCartas().add(carta7);
				saiyan.getCartas().add(carta8);
				saiyan.getCartas().add(carta10);
				saiyan.getCartas().add(carta13);
				saiyan.getCartas().add(carta15);
				saiyan.getCartas().add(carta16);
				saiyan.getCartas().add(carta17);
				saiyan.getCartas().add(carta20);
				saiyan.getCartas().add(carta22);
				saiyan.getCartas().add(carta27);
				saiyan.getCartas().add(carta28);
				saiyan.getCartas().add(carta29);
				saiyan.getCartas().add(carta30);
				saiyan.getCartas().add(carta31);
				saiyan.getCartas().add(carta32);
				saiyan.getCartas().add(carta33);

				gogeta.getCartas().add(carta22);
				gogeta.getCartas().add(carta28);
				gogeta.getCartas().add(carta29);
				gogeta.getCartas().add(carta30);

				vegito.getCartas().add(carta4);
				vegito.getCartas().add(carta5);
				vegito.getCartas().add(carta13);
				vegito.getCartas().add(carta32);
				vegito.getCartas().add(carta33);

				majin.getCartas().add(carta1);
				majin.getCartas().add(carta25);

				dualAttack.getCartas().add(carta2);
				dualAttack.getCartas().add(carta1);
				dualAttack.getCartas().add(carta20);
				dualAttack.getCartas().add(carta14);
				dualAttack.getCartas().add(carta12);

				quadrupleStrike.getCartas().add(carta33);
				quadrupleStrike.getCartas().add(carta14);
				quadrupleStrike.getCartas().add(carta30);

				barrier.getCartas().add(carta2);
				barrier.getCartas().add(carta27);
				barrier.getCartas().add(carta29);
				barrier.getCartas().add(carta30);
				barrier.getCartas().add(carta20);
				barrier.getCartas().add(carta12);
				barrier.getCartas().add(carta3);

				victoryStrike.getCartas().add(carta31);
				victoryStrike.getCartas().add(carta7);

				earthling.getCartas().add(carta20);
				earthling.getCartas().add(carta27);

				blocker.getCartas().add(carta2);
				blocker.getCartas().add(carta28);
				blocker.getCartas().add(carta20);
				blocker.getCartas().add(carta12);

				deflect.getCartas().add(carta2);
				deflect.getCartas().add(carta16);
				deflect.getCartas().add(carta14);
				deflect.getCartas().add(carta32);
				deflect.getCartas().add(carta33);

				energyExhaust.getCartas().add(carta2);
				energyExhaust.getCartas().add(carta5);
				energyExhaust.getCartas().add(carta14);
				energyExhaust.getCartas().add(carta28);

				doubleStrike.getCartas().add(carta18);
				doubleStrike.getCartas().add(carta4);

				tripleStrike.getCartas().add(carta17);
				tripleStrike.getCartas().add(carta5);
				tripleStrike.getCartas().add(carta32);

				activate.getCartas().add(carta27);
				activate.getCartas().add(carta26);
				activate.getCartas().add(carta1);
				activate.getCartas().add(carta24);
				activate.getCartas().add(carta23);
				activate.getCartas().add(carta29);
				activate.getCartas().add(carta30);
				activate.getCartas().add(carta3);
				activate.getCartas().add(carta21);
				activate.getCartas().add(carta20);
				activate.getCartas().add(carta19);
				activate.getCartas().add(carta9);
				activate.getCartas().add(carta4);
				activate.getCartas().add(carta32);
				activate.getCartas().add(carta18);
				activate.getCartas().add(carta15);
				activate.getCartas().add(carta17);
				activate.getCartas().add(carta16);
				activate.getCartas().add(carta13);
				activate.getCartas().add(carta12);
				activate.getCartas().add(carta11);
				activate.getCartas().add(carta10);
				activate.getCartas().add(carta8);
				activate.getCartas().add(carta31);
				activate.getCartas().add(carta7);
				activate.getCartas().add(carta6);

				permanent.getCartas().add(carta2);
				permanent.getCartas().add(carta23);
				permanent.getCartas().add(carta28);
				permanent.getCartas().add(carta22);
				permanent.getCartas().add(carta4);
				permanent.getCartas().add(carta32);
				permanent.getCartas().add(carta31);
				permanent.getCartas().add(carta13);
				permanent.getCartas().add(carta16);
				permanent.getCartas().add(carta17);

				categoriaRepositorio.save(saiyan);
				categoriaRepositorio.save(gogeta);
				categoriaRepositorio.save(vegito);
				categoriaRepositorio.save(majin);
				categoriaRepositorio.save(dualAttack);
				categoriaRepositorio.save(quadrupleStrike);
				categoriaRepositorio.save(barrier);
				categoriaRepositorio.save(victoryStrike);
				categoriaRepositorio.save(earthling);
				categoriaRepositorio.save(blocker);
				categoriaRepositorio.save(deflect);
				categoriaRepositorio.save(energyExhaust);
				categoriaRepositorio.save(doubleStrike);
				categoriaRepositorio.save(tripleStrike);
				categoriaRepositorio.save(activate);
				categoriaRepositorio.save(permanent);

			}
		};
	}
}