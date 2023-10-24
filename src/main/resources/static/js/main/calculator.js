/* 입력한 숫자 띄우기 */
function fn_number(num) {
    $("#window").text($("#window").text()+num)
}

/* 입력한 숫자 계산하기*/
function fn_cal() {
    const result = eval($("#window").text())
    $("#window").text(result)
}

/* 계산 리셋 */
function fn_reset() {
    $("#window").text("")
}