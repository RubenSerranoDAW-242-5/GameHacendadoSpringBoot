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
                        const maxCantidad = parseInt(inputCantidad.getAttribute('max'));
                        if (cantidadActual < maxCantidad) {
                            cantidadActual++;
                            inputCantidad.value = cantidadActual;
                            console.log(`Cantidad aumentada: ${cantidadActual}`);
                        } else {
                            console.log(`Cantidad máxima alcanzada: ${maxCantidad}`);
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
