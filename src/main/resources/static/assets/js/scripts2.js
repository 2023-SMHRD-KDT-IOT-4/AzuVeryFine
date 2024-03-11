// 상태를 저장할 변수들
let lastPhAlertTime = 0;
let lastTurbidAlertTime = 0;
let lastInFlowAlertTime = 0;
let lastOutFlowAlertTime = 0;

///////// 전역 변수로 센서 데이터 저장
window.sensorData = null;

window.addEventListener('DOMContentLoaded', event => {

    // 추가된 부분: 센서 데이터 확인 함수
    function checkSensorData() {
        $.get("/final/getGraphData", function(data) {
            console.log(data);
            

			////////////// 센서 데이터를 전역 변수에 저장
            window.sensorData = data;

            // ph 센서 데이터 확인
            if (data.phValue === null) {
                if (Date.now() - lastPhAlertTime > 5 * 60 * 1000) {
                    alert("ph 센서값을 못받아옴.");
                    lastPhAlertTime = Date.now(); // 알림 시간 갱신
                }
            } else {
                if(data.phValue >= 8) {
                    if (Date.now() - lastPhAlertTime > 5 * 60 * 1000) {
                        alert("ph 센서값이 높습니다");
                        lastPhAlertTime = Date.now(); // 알림 시간 갱신
                    }
                }
            }

            // 탁도 센서 데이터 확인
            if (data.turbidValue === null) {
                if (Date.now() - lastTurbidAlertTime > 5 * 60 * 1000) {
                    alert("탁도값을 못받아옴");
                    lastTurbidAlertTime = Date.now(); // 알림 시간 갱신
                }
            } else {
                if(data.turbidValue >= 4) {
                    if (Date.now() - lastTurbidAlertTime > 5 * 60 * 1000) {
                        alert("탁도값이 높습니다");
                        lastTurbidAlertTime = Date.now(); // 알림 시간 갱신
                    }
                }
            }

            // 유입량 센서 데이터 확인
            if (data.inFlowValue === null) {
                if (Date.now() - lastInFlowAlertTime > 5 * 60 * 1000) {
                    alert("유입량값을 못받아옴");
                    lastInFlowAlertTime = Date.now(); // 알림 시간 갱신
                }
            }

            // 유출량 센서 데이터 확인
            if (data.outFlowValue === null) {
                if (Date.now() - lastOutFlowAlertTime > 5 * 60 * 1000) {
                    alert("유출량값을 못받아옴");
                    lastOutFlowAlertTime = Date.now(); // 알림 시간 갱신
                }
            }
        });
    }
	checkSensorData(); // 페이지 로드와 함께 checkSensorData 함수를 한 번 실행
	
    // 일정 시간마다 checkSensorData 함수 호출
   // setInterval(checkSensorData, 5000); // 5초마다 호출
   setInterval(function () {
            // 새로운 데이터 가져와서 업데이트
            console.log("스크립트");            
			checkSensorData();
            
        }, 10000);
	
});