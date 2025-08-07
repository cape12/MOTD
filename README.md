# MOTD 플러그인

마인크래프트 서버용 MOTD(Message Of The Day) 변경 플러그인입니다.  
서버 접속 시 보여지는 메시지를 커스터마이징할 수 있으며, 서버 아이콘 변경과 최대 플레이어 수 조정 기능도 포함되어 있습니다.

## 주요 기능
- MiniMessage 포맷을 MOTD 수정
- config.yml을 통한 손쉬운 설정  
- 커스텀 서버 아이콘 경로 지정 및 자동 복사 기능  
- MOTD 설정 리로드 명령어 제공 (`/motd reload`)  
- 최대 플레이어 수 동적 설정  

## 설치 방법
1. 최신 버전의 플러그인을 다운로드합니다.  
2. 플러그인을 `plugins` 폴더에 넣고 서버를 재시작하세요.  
3. `plugins/MOTD` 폴더에 있는 `config.yml` 파일에서 MOTD 및 서버 아이콘 설정을 조정하세요.

## 설정 파일 수정 방법

플러그인의 동작을 변경하려면 `config.yml` 파일을 수정하세요. 주요 설정 항목은 다음과 같습니다:

```yaml
# motd 기능을 사용할 지 여부
use-motd: true

# miniMessage 문법을 참고해주세요
motd:
  - "<gradient:#8000ff:#ffffff><bold>⚡ MOTD 플러그인 기본 텍스트 ⚡</bold></gradient>"
  - "<white>서버에 오신 걸 환영합니다!</white>"

# server-icon 기능을 사용할 지 여부
use-server-icon: true

# server-icon 폴더에 png 사진을 넣어주세요 (사진 사이즈 64x64 추천)
server-icon-path: "server-icon.png"

# 서버 최대 플레이어 설정
max-players: 20231018
```

## 명령어
- `/motd reload` : 설정 파일을 다시 불러옵니다.

## 요구 사항
- Paper 1.21.8 이상  
- Java 21  

## 이스터에그
- `config.yml` 파일에 있는 `max-players: 20231018` 해당 설정에서 **20231018**은 QWER의 데뷔일입니다.
