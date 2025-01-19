document.addEventListener('DOMContentLoaded', function () {
    console.log("DOM cargado");

    const gridContainer = document.getElementById('grid');

    function registrarEventos() {
        const botonesMas = document.querySelectorAll('.btn-mas');
        const botonesMenos = document.querySelectorAll('.btn-menos');

        if (botonesMas.length > 0 && botonesMenos.length > 0) {
            botonesMas.forEach(boton => {
                boton.addEventListener('click', function () {
                    const idCarta = this.getAttribute('data-id');
                    const inputCantidad = document.getElementById(`cantidad-${idCarta}`);
                    if (inputCantidad) {
                        let cantidadActual = parseInt(inputCantidad.value);
                        const cantidadMax = inputCantidad.max;

                        if (cantidadActual < cantidadMax) {
                            cantidadActual++;
                            inputCantidad.value = cantidadActual;
                            console.log(`Cantidad aumentada: ${cantidadActual}`);
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

                        if (cantidadActual > 1) {
                            cantidadActual--;
                            inputCantidad.value = cantidadActual;
                            console.log(`Cantidad disminuida: ${cantidadActual}`);
                        }
                    }
                });
            });
        }
    }

    registrarEventos();


    const observer = new MutationObserver(() => {
        registrarEventos();
    });

    observer.observe(gridContainer, { childList: true, subtree: true });
});
