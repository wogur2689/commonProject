/**
 * 공통 js 함수
 */

// <!-- 팝업 -->
function commonAlert() {

}

function commonPrompt() {

}

function commonConfirm() {

}
// <!-- 팝업 -->

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
    var paymentPage = false;
    if(pay) {
            //결제완료 페이지면 true
            paymentPage = true;
    }
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
        , type: "POST"
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
        console.log(xhr);
        if (xhr.status == "401") {
            fnUserAlt("<spring:message code='script.author.fail' />", "", function() {
                location.href = "/serLogout.do";
            });
        } else if (xhr.status == "403") {
//        fnUserAlt("<spring:message code='script.session.fail' />", "", function() {
//           location.href = "/serLogout.do";
//       });
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