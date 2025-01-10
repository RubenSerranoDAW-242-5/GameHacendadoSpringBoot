document.addEventListener("DOMContentLoaded", function() {
    const errorMessage = document.getElementById("mensajeError");
    if (errorMessage) {
        setTimeout(() => {
            errorMessage.style.display = "none";
        }, 5000);
    }
});

const form = document.querySelector("form");
form.addEventListener("submit", function() {
    const mensajeError = document.getElementById("mensajeError");
    if (mensajeError) {
        mensajeError.style.display = "none"; // Oculta el mensaje al enviar el formulario
    }
});