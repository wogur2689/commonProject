//공통 변수
var num 	= pw.search(/[0-9]/g);
var eng 	= pw.search(/[a-z]/ig);
var spe 	= pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

/**
 * 이메일 유효성 체크
 * param	:	이메일
 * return	:	true, false
 */
function fnCheckEmail(emailText) {
    var pattern = new RegExp(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/);

    if(!pattern.test(emailText)) {
        fnUserAlt("올바른 이메일 형식이 아닙니다.");
        return false;
    }

    return true;
}

/**
 * 비밀번호 유효성 체크
 * param	:	문자열, 오류메시지
 * return	:	true, false
 */
function fnCheckPwd(pw){
    if(pw.length < 8 || pw.length > 13){
        fnUserAlt("비밀번호는 8자 이상, 13자 이하입니다.");
        return false;
    }

    if(pw.search(/\s/) != -1){
        fnUserAlt("비밀번호는 공백없이 입력해주세요.");
        return false;
    }

    if(num < 0 || eng < 0 || spe < 0 ){
        fnUserAlt("문자, 숫자, 특수문자가 모두 포함되어야 합니다.");
        return false;
    }

    return true;
}

/**
 * 아이디 체크
 * param	:	문자열
 * return	:	true, false
 */
function fnCheckId(id) {
    var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi; //특수문자 금지
    var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; //한글 금지
    var checkBig = /^[A-Z]$/g; // 대문자 금지
    var chk = false;

    for (var i = 0; i<id.length; i++) {
        if(checkBig.test(id[i])){
            chk = true;
            fnUserAlt("대문자는 입력 할 수 없습니다.", "", function() {
                $("#id").focus();
            });
            break;
        }
    }
    if(chk){
        return false;
    }

    if(id.substr(0,1).search(/[0-9]/g) == 0){
        fnUserAlt("영문으로 시작하는 영문소문자,숫자5~13자 입력해주세요.", "", function() {
            $("#username").focus();
        });
        return false;
    }
    if(regExp.test(id)){
        fnUserAlt("특수문자는 입력 할 수 없습니다.", "", function() {
            $("#username").focus();
        });
        return false;
    }
    if(check.test(id)){
        fnUserAlt("한글은 입력 할 수 없습니다.", "", function() {
            $("#username").focus();
        });
        return false;
    }

    if(id.length < 5 || id.length > 13){
        fnUserAlt("아이디는 5자 이상, 13자 이하로 입력해주세요.", "", function() {
            $("#username").focus();
        });
        return false;
    }

    if(id.search(/\s/) != -1){
        fnUserAlt("아이디는 공백없이 입력해주세요.", "", function() {
            $("#username").focus();
        });
        return false;
    }
    if(eng < 0 || spe > 0){
        fnUserAlt("ID는 영문소문자,숫자5~13자리조합만 가능합니다.", "", function() {
            $("#username").focus();
        });
        return false;
    }
    return true;
}