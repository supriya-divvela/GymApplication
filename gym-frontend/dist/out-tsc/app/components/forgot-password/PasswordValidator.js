export const PasswordValidator = (control) => {
    const password = control.get('newPassword');
    const confirmpassword = control.get('confirmPassword');
    if (password && confirmpassword && password.value != confirmpassword.value) {
        return {
            passwordmatcherror: true
        };
    }
    return null;
};
export const StrongPasswordRegx = /^(?=[^A-Z]*[A-Z])(?=[^a-z]*[a-z])(?=\D*\d).{8,}$/;
//# sourceMappingURL=PasswordValidator.js.map