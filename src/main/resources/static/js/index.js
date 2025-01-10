document.addEventListener('DOMContentLoaded', function () { 
    console.log("DOM cargado");
    // Seleccionamos todos los botones de más y menos
    const botonesMas = document.querySelectorAll('.btn-mas');
    const botonesMenos = document.querySelectorAll('.btn-menos');

    // Aseguramos que los botones existen antes de agregarles el evento
    if (botonesMas.length > 0 && botonesMenos.length > 0) {
        botonesMas.forEach(boton => {
            boton.addEventListener('click', function () {
                const idCarta = this.getAttribute('data-id');
                const inputCantidad = document.getElementById(`cantidad-${idCarta}`);
                if (inputCantidad) {
                    let cantidadActual = parseInt(inputCantidad.value);
                    const cantidadMax = inputCantidad.max;

                    // Incrementar la cantidad pero no sobrepasar el máximo
                    if (cantidadActual < cantidadMax) {
                        cantidadActual++;
                        inputCantidad.value = cantidadActual;
                        console.log(inputCantidad.value);
                        // console.log(cantidadActual+"+1 idCarta "+idCarta);
                    }
                }
            });
        });

        botonesMenos.forEach(boton => {
            boton.addEventListener('click', function () {
                const idCarta = this.getAttribute('data-id');
                const inputCantidad = document.getElementById(`cantidad-${idCarta}`);
                if (inputCantidad) {
                    let cantidadActual = parseInt(inputCantidad.value);

                    // Decrementar la cantidad pero no bajar de 1
                    if (cantidadActual > 1) {
                        cantidadActual--;
                        inputCantidad.value = cantidadActual;
                        console.log(inputCantidad.value);
                        // console.log(cantidadActual+"-1 idCarta "+idCarta);
                    }
                }
            });
        });
    } else {
        console.error("Los botones de incremento y decremento no se han encontrado.");
    }
});
