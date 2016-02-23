/**
 * Created by pc8 on 16.09.15.
 */

function checkPw(form) {
    pw1 = form.pasword.value;
    pw2 = form.repeadPassword.value;

    if (pw1 != pw2) {
        alert ("\nВы ввели в поле \"Повторить\" пароль отличный от введенного в поле \"Пароль\".")
        return false;
    }
    else return true;
}