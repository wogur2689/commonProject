/**
 * 공통 js 함수
 */

$(function () {
    //tap
    $('input[tap]').keyup(function () {
        var max = $(this).attr("maxlength");
        if (this.value.length >= max) {
            $(this).next('input[tap]').focus();
            return false;
        }
    });

    /* input유효성 검사 - input box 마지막에 붙여주기 */
    // 숫자만 입력받기 START	(주민등록번호..)
    $("input[onlyNum]").on("keydown", function (event) {
        this.pattern = "[0-9]*";
        event = event || window.event;
        var keyID = (event.which) ? event.which : event.keyCode;
        if ((keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 9 || keyID == 16 || keyID == 46 || keyID == 37 || keyID == 39)
            return;
        else
            return false;
    });
    $("input[onlyNum]").on("keyup", function () {
        var regex = /[^0-9]/gi;
        var v = $(this).val();
        if (regex.test(v)) {
            $(this).val(v.replace(regex, ''));
        }
    });
    // 숫자만 입력받기 END

    // 영문/한글만 입력받기 START (NAME)
    $("input[onlyEngHan]").on("keyup", function () {
        var regex = /[^ㄱ-힣ㆍᆢa-zA-Z]/gi;
        var v = $(this).val();
        if (regex.test(v)) {
            $(this).val(v.replace(regex, ''));
        }
    });
    // 영문/한글만 입력받기 END

    // 영문/숫자만 입력받기 START (ID)
    $("input[onlyEngNum]").on("keyup", function () {
        var regex = /[^a-zA-Z0-9]/gi;
        var v = $(this).val();
        if (regex.test(v)) {
            $(this).val(v.replace(regex, ''));
        }
    });
    // 영문/숫자 입력받기 END

    //드롭다운
    $(".dropdown").onclick(() => {
        $(".dropdown").dropdown('show');
        }
    )
});

/**
 * 페이지 전환
 * urls : 페이지 경로
 */
function fnChangePage(urls) {
    // if(urls.indexOf('logout.do') > -1) {
    //     fnUserCfmAlt("<spring:message code='login.confm.logout'/>", "", function (flag) {
    //         if(flag) {
    //             location.replace(urls);
    //         }
    //     });
    // } else {
        location.replace(urls);
    //}
}
/**
 * str1 문자열이 빈값일 경우 str2 리턴
 * param	:	문자열, 치환문자열
 * return	:	문자열
 */
function nvl(str1, str2) {

    str1 = $.trim(str1);
    if(str1 != '' && str1 != null && str1 != 'null' && str1 !== undefined && str1 != 'undefined') {
        return str1;
    }

    return str2;
}

/**
 * 문자열 유효성 체크
 * param	:	문자열
 * return	:	true, false
 */
function fnCheckValidStr(str) {
    var trimStr = $.trim(str);
    if (trimStr != "" && trimStr != null && trimStr != "null" && typeof trimStr != "undefined") {
        return true;
    }
    return false;
}

// api
/**
* 공통 AJAX
* urls               : 호출 URL정보
* param              : 파라미터정보. 폼 Object || 파라미터 문자열
* isSync             : 동기 / 비동기 설정
* targetObj          : 대상 폼 Object || null
* functionToCallBack : 리턴받을 함수 함수형태로 던졌을 경우에만 콜백 발생
* successMsg         : 성공 시 메시지
* failMessage        : 실패 시 메시지
*/
function commonAjax(urls, param, isSync, targetObj, functionToCallBack , succMessage, failMessage) {
    if(nvl(urls, '') == '') return;
    // 기본은 비동기 방식
    var isAsync = true;
    // isSync 타입이 boolean 값일 경우 처리
    if (typeof isSync == 'boolean') {
        isAsync = isSync;
    }
    var params = JSON.stringify(param);
    //무조건 json으로만 처리도록 설정.
    var contentTypeNm = "application/json; charset=UTF-8";
    // // param 타입이 object일 경우 폼 Object로 판단하여 해당 폼 정보 담기
    // if(typeof param == 'object') {
    //     try {
    //         params = param.serialize();
    //     } catch(e) {
    //         // 페이징대상 폼 Object의 Paging 관련 처리
    //         if (nvl(targetObj, '') != '' && typeof targetObj == 'object') {
    //             param['currPageIdx'] = targetObj.find("#currPageIdx").val();
    //             param['totPageCnt'] = targetObj.find("#totPageCnt").val();
    //             param['pageSize'] = targetObj.find("#pageSize").val();
    //         }
    //         // parameter 배열이 넘어온다면
    //         // Java Controller 에서 @RequestBody로 처리 할 수 있도록 ContentType JSON으로 변경
    //         if (Array.isArray(param)) {
    //             params = JSON.stringify(param);
    //         } else {
    //             params = param;
    //         }
    //     }
    // }
    var rtnData = '';
    var request = $.ajax({
        url: urls
        , type: "POST"
        , dataType: "json"
        , data: params
        , async: isAsync
        , contentType : contentTypeNm
    });
    request.done(function(resultData) {
        // if(nvl(targetObj, '') != '' && typeof targetObj == 'object') {
        //     fnCreatePaging(resultData, targetObj);
        // }
        if (nvl(succMessage, '') != '') {
            fnUserAlt(succMessage);
        }
        if (typeof functionToCallBack == 'function') {
            functionToCallBack(resultData);
        } else {
            rtnData = resultData;
        }
    }).fail(function(xhr, request, status) {
        // hideLoadingBar();
        // appendLoadingbar();
        var str = xhr.responseText;
        if (xhr.status == "401") {
            fnUserAlt("로그인 해주세요.", "", function() {
                location.href = "/login";
            });
        } else if (xhr.status == "403") {
//        fnUserAlt("<spring:message code='script.session.fail' />", "", function() {
//           location.href = "/serLogout.do";
//       });
        } else if (xhr.status == "404") {
            fnUserAlt("화면 에러입니다.");
        } else if(nvl(failMessage, '') != '') {
            fnUserAlt(failMessage);
        } else if(fnCheckValidStr(str)) {
            fnUserAlt(str);
        } else {
            fnUserAlt(str);
        }
    });
    // 비동기 방식에 return type이 함수가 아닐 경우 결과값 리턴
    if(!isAsync && typeof functionToCallBack != 'function') {
        return rtnData;
    }
}


/**
* 공통 Get AJAX
* urls              : 호출 URL정보
* param             : 파라미터정보. 폼 Object || 파라미터 문자열
* isSync            : 동기 / 비동기 설정
* targetObj         : 대상 폼 Object || null
* functionToCallBack: 리턴받을 함수 함수형태로 던졌을 경우에만 콜백 발생
* successMsg        : 성공 시 메시지
* failMessage       : 실패 시 메시지
*/
function commonGetAjax(urls, param, isSync, targetObj, functionToCallBack , succMessage, failMessage) {
    if(nvl(urls, '') == '') return;
    // 기본은 비동기 방식
    var isAsync = true;
    // isSync 타입이 boolean 값일 경우 처리
    if (typeof isSync == 'boolean') {
        isAsync = isSync;
    }
    var params = param;
    var contentTypeNm = "application/x-www-form-urlencoded";
    // param 타입이 object일 경우 폼 Object로 판단하여 해당 폼 정보 담기
    if(typeof param == 'object') {
        try {
            params = param.serialize();
        } catch(e) {
            // 페이징대상 폼 Object의 Paging 관련 처리
            if (nvl(targetObj, '') != '' && typeof targetObj == 'object') {
                param['currPageIdx'] = targetObj.find("#currPageIdx").val();
                param['totPageCnt'] = targetObj.find("#totPageCnt").val();
                param['pageSize'] = targetObj.find("#pageSize").val();
            }
            // parameter 배열이 넘어온다면
            // Java Controller 에서 @RequestBody로 처리 할 수 있도록 ContentType JSON으로 변경
            if (Array.isArray(param)) {
                contentTypeNm = "application/json; charset=UTF-8";
                params = JSON.stringify(param);
            } else {
                params = param;
            }
        }
    }
    var rtnData = '';
    var request = $.ajax({
        url: urls
        , type: "GET"
        , dataType: "json"
        , data: params
        , async: isAsync
        , contentType : contentTypeNm
    });
    request.done(function(resultData) {
        if(nvl(targetObj, '') != '' && typeof targetObj == 'object') {
            fnCreatePaging(resultData, targetObj);
        }
        if (nvl(succMessage, '') != '') {
            fnUserAlt(succMessage);
        }
        if (typeof functionToCallBack == 'function') {
            functionToCallBack(resultData);
        } else {
            rtnData = resultData;
        }
    }).fail(function(xhr, request, status) {
        hideLoadingBar();
        appendLoadingbar();
        var str = xhr.responseText;
        if (xhr.status == "401") {
            fnUserAlt("<spring:message code='script.author.fail' />", "", function() {
                location.href = "/serLogout.do";
            });
        } else if (xhr.status == "403") {
//             fnUserAlt("<spring:message code='script.session.fail' />", "", function() {
//                 location.href = "/serLogout.do";
//             });
        } else if (xhr.status == "404") {
            fnUserAlt("<spring:message code='script.fail.not.found' />", "", function() {
                location.href = "/serLogout.do";
            });
        } else if(nvl(failMessage, '') != '') {
            fnUserAlt(failMessage);
        } else if(fnCheckValidStr(str)) {
            fnUserAlt(str);
        } else {
            fnUserAlt(str);
        }
    });
    // 비동기 방식에 return type이 함수가 아닐 경우 결과값 리턴
    if(!isAsync && typeof functionToCallBack != 'function') {
        return rtnData;
    }
}


/***
 * 사용자 확인얼럿
 */
function fnUserCfmAlt(msg, title = "알림", callback) {
    const modalId = "userAlertModal_" + Date.now();

    const html = `
    <div class="modal fade" id="${modalId}" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content shadow">
          <div class="modal-header">
            <h5 class="modal-title">${title}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="modal-body text-center">
            ${msg}
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="${modalId}_ok">확인</button>
            <button type="button" class="btn btn-primary" id="${modalId}_no">취소</button>
          </div>
        </div>
      </div>
    </div>`;

    $("body").append(html);

    const modal = new bootstrap.Modal(document.getElementById(modalId));
    modal.show();

    $(`#${modalId}_ok`).on("click", () => {
        modal.hide();
        if (callback) callback();
    });

    $(`#${modalId}_no`).on("click", () => {
        modal.hide();
        if (callback) callback();
    });

    $(`#${modalId}`).on("hidden.bs.modal", () => {
        $(`#${modalId}`).remove();
    });
}

/***
 * 사용자 얼럿
 */
function fnUserAlt(msg, title = "알림", callback) {
    const modalId = "userAlertModal_" + Date.now();

    const html = `
    <div class="modal fade" id="${modalId}" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content shadow">
          <div class="modal-header">
            <h5 class="modal-title">${title}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="modal-body text-center">
            ${msg}
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="${modalId}_ok">확인</button>
          </div>
        </div>
      </div>
    </div>`;

    $("body").append(html);

    const modal = new bootstrap.Modal(document.getElementById(modalId));
    modal.show();

    $(`#${modalId}_ok`).on("click", () => {
        modal.hide();
        if (callback) callback();
    });

    $(`#${modalId}`).on("hidden.bs.modal", () => {
        $(`#${modalId}`).remove();
    });
}