document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('registrationForm');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirmPassword');
    const confirmPasswordFeedback = document.querySelector('#confirmPassword ~ .invalid-feedback');
    const roleRadios = document.getElementsByName('role');
    const subscribeCheckbox = document.querySelector('.subscribe');
    const firstNameInput = document.getElementById('fullName');
    const lastNameInput = document.getElementById('telephone');
    const emailInput = document.getElementById('email');

    // Function to toggle the display of the newsletter checkbox
    function toggleSubscribeCheckbox() {
        const isCustomerSelected = Array.from(roleRadios).some(radio => radio.checked && radio.value === 'customer');
        subscribeCheckbox.style.display = isCustomerSelected ? 'block' : 'none';
    }

    // Add event listener to radio buttons to toggle checkbox visibility
    roleRadios.forEach(radio => {
        radio.addEventListener('change', toggleSubscribeCheckbox);
    });

    // Function to clear validation errors
    function clearErrors() {
        document.querySelectorAll('.form-control').forEach(el => el.classList.remove('is-invalid'));
        document.querySelectorAll('.invalid-feedback').forEach(el => el.style.display = 'none');
    }

    // Function to validate the form fields
    function validateForm() {
        clearErrors();
        let isValid = true;

        // Validate first name
        if (!firstNameInput.value.trim()) {
            document.querySelector('#fullName ~ .invalid-feedback').style.display = 'block';
            firstNameInput.classList.add('is-invalid');
            isValid = false;
        }

        // Validate last name
        if (!lastNameInput.value.trim()) {
            document.querySelector('#telephone ~ .invalid-feedback').style.display = 'block';
            lastNameInput.classList.add('is-invalid');
            isValid = false;
        }

        // Validate email
        if (!emailInput.value.trim() || !emailInput.checkValidity()) {
            document.querySelector('#email ~ .invalid-feedback').style.display = 'block';
            emailInput.classList.add('is-invalid');
            isValid = false;
        }

        // Validate password
        if (!passwordInput.value.trim()) {
            document.querySelector('#password ~ .invalid-feedback').style.display = 'block';
            passwordInput.classList.add('is-invalid');
            isValid = false;
        }

        // Validate confirm password only if not empty
        if (confirmPasswordInput.value.trim() !== '' && passwordInput.value !== confirmPasswordInput.value) {
            confirmPasswordFeedback.style.display = 'block';
            confirmPasswordInput.classList.add('is-invalid');
            isValid = false;
        } else {
            confirmPasswordFeedback.style.display = 'none';
            confirmPasswordInput.classList.remove('is-invalid');
        }

        return isValid;
    }

    // Function to validate confirm password on input
    function validateConfirmPassword() {
        if (confirmPasswordInput.value.trim() === '') {
            confirmPasswordFeedback.style.display = 'none';
            confirmPasswordInput.classList.remove('is-invalid');
        } else if (passwordInput.value !== confirmPasswordInput.value) {
            confirmPasswordFeedback.style.display = 'block';
            confirmPasswordInput.classList.add('is-invalid');
        } else {
            confirmPasswordFeedback.style.display = 'none';
            confirmPasswordInput.classList.remove('is-invalid');
        }
    }

    // Add event listener for confirm password field to validate on input
    confirmPasswordInput.addEventListener('input', validateConfirmPassword);

    // Add event listener for password field to validate confirm password
    passwordInput.addEventListener('input', validateConfirmPassword);

    // Add event listeners to clear error messages on input for other fields
    [firstNameInput, lastNameInput, emailInput, passwordInput, confirmPasswordInput].forEach(input => {
        input.addEventListener('input', () => {
            if (input.value.trim() !== '') {
                input.classList.remove('is-invalid');
                const feedback = input.nextElementSibling;
                if (feedback && feedback.classList.contains('invalid-feedback')) {
                    feedback.style.display = 'none';
                }
            }
        });
    });

    // Handle form submission
    form.addEventListener('submit', (event) => {
        if (!validateForm()) {
            event.preventDefault(); // Prevent form submission if validation fails
        }
    });

    // Initial call to set the correct state of the checkbox
    toggleSubscribeCheckbox();
});