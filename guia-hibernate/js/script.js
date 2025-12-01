// --- FUNCIÓN COPIAR CÓDIGO ---
function copyCode(elementId) {
    const copyText = document.getElementById(elementId).innerText;
    navigator.clipboard.writeText(copyText).then(() => {
        const btn = document.querySelector('.copy-btn');
        const originalText = btn.innerText;
        btn.innerText = '¡Copiado!';
        btn.style.background = '#4caf50';
        btn.style.borderColor = '#4caf50';
        
        setTimeout(() => {
            btn.innerText = originalText;
            btn.style.background = 'transparent';
            btn.style.borderColor = '#555';
        }, 2000);
    });
}

// --- ACTIVE LINK HIGHLIGHT ---
document.addEventListener('DOMContentLoaded', () => {
    const currentPath = window.location.pathname.split('/').pop() || 'index.html';
    const navLinks = document.querySelectorAll('.nav-link');
    
    navLinks.forEach(link => {
        link.classList.remove('active');
        if (link.getAttribute('href') === currentPath) {
            link.classList.add('active');
        }
    });
});
