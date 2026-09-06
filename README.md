# Độ Nhạy Minh Đức – Android Studio

Phiên bản enhanced:
- Giữ nguyên file HTML gốc trong `app/src/main/assets/index.html`.
- WebView chạy offline từ assets, không có thanh trình duyệt.
- Splash screen khi mở app.
- Icon ứng dụng riêng.
- Tên app: Độ Nhạy Minh Đức.
- Portrait, cuộn/chạm theo HTML.
- Hỗ trợ JavaScript, local storage và media.
- Nút Back Android quay lại trang trước trong WebView.

## Build APK
1. Cài Android Studio.
2. Mở thư mục `DoNhayMinhDuc-Android-Studio-Enhanced`.
3. Để Android Studio tải Gradle/SDK.
4. Chọn `Build > Build App Bundle(s) / APK(s) > Build APK(s)`.
5. APK debug thường nằm trong `app/build/outputs/apk/debug/app-debug.apk`.

Lưu ý: môi trường hiện tại không có Android SDK/Gradle nên mình không thể tự đóng gói APK tại đây.
