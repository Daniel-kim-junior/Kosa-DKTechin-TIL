# docker Network

veth : virtual eth(Container inner Network ip Hash value)

docker0: 도커 엔진에 의해 기본 생성되는 브릿지 네트워크 => veth와 eth 간 다리 역할

eth0: 서버 내부의 이더넷0번 ip

### 컨테이너 서비스 노출

    docker run -p 80:80 nginx
    - 컨테이너 포트 80 번을 0.0.0.0:80번 ip로 Exposing
    docker run -p 127.0.0.1:80:80 nginx
    - 컨테이너 포트 80 번을 127.0.0.1:80번 특정 ip로 Exposing
    docker run -p 80 nginx
    - nginx 컨테이너의 80번 포트를 호스트의 사용 가능한 포트로(랜덤) Exposing

### Expose vs Publish

    - expose 옵션은 그저 문서화 용도(기능 없음)
    docker run -d --expose 80 nginx

    - publish 옵션은 실제 포트를 바인딩
    docker run -d -p 80 nginx


## docker Network driver

##  Native Drivers(기본 네트워크) - 빌트인
    
### Single Host Network
    - Bridge : 사용자 정의 네트워크 (컨테이너들끼리 서로 같은 브릿지 네트워크 형성)
    - Host : Host에 직접 바인딩 되기때문에 포트만 Expose
    - None : 네트워크 정의 x

    - Overlay: Multi Host Networking(Dokcer Swarm)
## Remote Drivers(커스텀 네트워크)
    - 3rd-Party Plugins


