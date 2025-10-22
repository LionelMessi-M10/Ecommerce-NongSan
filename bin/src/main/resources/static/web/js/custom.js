function showToast(message, type = 'info') {
	const container = document.getElementById('toast-container');

	const icons = {
		success: `<svg fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
   	      <path stroke-linecap="round" stroke-linejoin="round" d="M5 13l4 4L19 7" />
   	    </svg>`,
		error: `<svg fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
   	      <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
   	    </svg>`,
		warning: `<svg fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
   	      <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v2m0 4h.01M12 5c.92 0 1.75.39 2.35 1.01l6.29 6.3a3.3 3.3 0 010 4.67L14.35 21.7A3.3 3.3 0 0110 21.7L3.35 15a3.3 3.3 0 010-4.67l6.29-6.3A3.3 3.3 0 0112 5z"/>
   	    </svg>`,
		info: `<svg fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
   	      <path stroke-linecap="round" stroke-linejoin="round" d="M13 16h-1v-4h-1m1-4h.01M12 2a10 10 0 100 20 10 10 0 000-20z"/>
   	    </svg>`
	};

	const toast = document.createElement('div');
	toast.className = `toast ${type}`;
	toast.innerHTML = `
   	    ${icons[type] || ''}
   	    <span>${message}</span>
   	    <button class="close-btn" aria-label="Close" title="Đóng">×</button>
   	    <div class="toast-progress"></div>
   	  `;

	container.appendChild(toast);

	// Xử lý nút đóng thủ công, chạy animation rồi mới remove
	toast.querySelector('.close-btn').addEventListener('click', () => {
		closeToast(toast);
	});

	// Tự động đóng sau 3s, chạy animation rồi mới remove
	setTimeout(() => {
		closeToast(toast);
	}, 3000);
}

function closeToast(toast) {
	// Thêm animation trượt sang phải rồi remove sau khi xong animation
	toast.style.animation = 'slideOutRight 0.3s forwards';
	toast.addEventListener('animationend', () => {
		toast.remove();
	});
}

function setTheme(theme) {
	document.body.className = theme + '-theme';
	localStorage.setItem('theme', theme);
}

(function() {
	const savedTheme = localStorage.getItem('theme');
	if (savedTheme) {
		setTheme(savedTheme);
	}
})();



