# SOON Autobiography Android App

## 프로젝트 개요

`android/` 디렉터리에는 Jetpack Compose, Hilt, Room, WorkManager, Retrofit2, Kotlinx Serialization, Security Crypto 등을 사용하는 안드로이드 애플리케이션이 포함되어 있습니다. 패키지명은 `com.yourcompany.biography` 입니다.

## 개발 환경 준비

1. **Android Studio** 최신 버전 설치 (또는 설치된 JDK 17과 Android SDK 34).
2. `ANDROID_HOME` 또는 `ANDROID_SDK_ROOT` 환경변수가 올바르게 설정되어 있는지 확인합니다.
3. 리포지토리 루트에서 안드로이드 프로젝트 디렉터리로 이동합니다.

```bash
cd android
```

> ⚠️ 현재 저장소에는 `gradle-wrapper.jar` 등 바이너리 파일이 포함되어 있지 않습니다. 프로젝트 빌드 전에 아래 명령으로 Gradle Wrapper를 생성하거나, 로컬에 설치된 Gradle을 사용해 주세요.
>
> ```bash
> gradle wrapper
> ```
>
> 또는 Android Studio가 자동으로 Wrapper 파일을 내려받도록 둘 수 있습니다.

## 빌드 및 실행

Gradle Wrapper를 준비한 뒤 다음 명령으로 디버그 빌드를 수행할 수 있습니다.

```bash
./gradlew assembleDebug
```

Android Studio에서 `Run` ▶︎ `Run 'app'`을 실행하면 에뮬레이터 또는 연결된 기기에서 앱을 실행할 수 있습니다.

## 권한

앱은 생애 기록을 위한 오디오 녹음을 위해 다음과 같은 권한이 필요합니다.

- `android.permission.RECORD_AUDIO`
- `android.permission.POST_NOTIFICATIONS` (Android 13 이상에서 녹음 진행 알림 제공 시 필요)
- 필요에 따라 `android.permission.READ_MEDIA_AUDIO` 또는 저장소 권한

`AndroidManifest.xml`에는 관련 TODO 주석이 포함되어 있으며, 실제 녹음 기능을 연결할 때 권한과 Foreground Service 타입을 정확히 지정해 주세요. 런타임 권한 요청 로직 역시 구현이 필요합니다.

## 주요 구성 요소

- **Jetpack Compose UI**: `ui/` 패키지에 메인 화면, 녹음 컨트롤, 질문 위저드 UI가 위치합니다.
- **Hilt 의존성 주입**: `di/` 패키지에서 Retrofit, Room, Repository, Dispatcher 등을 제공합니다.
- **Room**: `data/local/` 패키지에 데이터베이스, DAO, 엔티티가 정의되어 있습니다.
- **Remote API**: `data/remote/` 패키지에 Retrofit 서비스 인터페이스가 정의되어 있으며 Kotlinx Serialization으로 응답을 파싱합니다.
- **Repository**: `data/repo/` 패키지가 로컬/원격 데이터를 통합합니다.
- **WorkManager**: `worker/RecordingWorker`가 백그라운드 동기화를 담당하도록 준비되어 있습니다.
- **EncryptedFile**: `util/EncryptedFileManager`가 민감한 데이터를 안전하게 보관할 수 있도록 도와줍니다.

## 접근성

메인 화면에는 최소 높이 48dp의 “녹음 시작” 버튼이 배치되어 있으며, TalkBack 사용자를 위한 접근성 라벨이 포함되어 있습니다. 큰 글씨 모드에서도 읽기 쉬운 `MaterialTheme.typography.titleLarge` 스타일을 사용합니다.

## TODO

- 실제 녹음 기능과 Foreground Service 동작 연결
- 질문 위저드와 데이터 연동
- 런타임 권한 요청 UX 구현
- 백엔드 API 연동 및 에러 처리 고도화
