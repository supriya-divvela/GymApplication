import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export const PasswordValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const password = control.get('newPassword');
  const confirmpassword = control.get('confirmPassword');
  if (password && confirmpassword && password.value !== confirmpassword.value) {
    return {
      passwordmatcherror: true
    }
  }
  return null;
}

export const CaptchaValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const captcha = control.get('captcha');
  const reentercaptch = control.get('reentercaptcha');
  console.log(captcha?.value+" "+reentercaptch?.value);
  if (captcha && reentercaptch && captcha.value !== reentercaptch.value) {
    return {
      captchaerror: true
    }
  }
  return null;
}