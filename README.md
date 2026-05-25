Office Maintenance Management System (Hệ thống Quản lý Bảo trì Văn phòng)

🔗 Jira Task Board: https://thuong1703n.atlassian.net/jira/software/projects/OMMS/boards/34

1. Project Overview
This project is a comprehensive facility management platform that allows staff to report issues, helps managers assign tasks to technicians, tracks repair progress, and schedules preventive maintenance for office equipment.
(Dự án này là nền tảng quản lý cơ sở vật chất toàn diện, cho phép nhân viên báo cáo sự cố thiết bị/văn phòng, giúp quản lý phân công tác vụ cho kỹ thuật viên, theo dõi tiến độ sửa chữa và lên lịch bảo trì phòng ngừa cho các thiết bị.)

2. Research By Learning (RBL) Focus
Trong dự án này, nhóm tập trung nghiên cứu và áp dụng các kiến thức chuyên sâu vào các khía cạnh sau (In this project, our RBL focuses on):

Thuật toán (Algorithms):

Priority & Workload-based Assignment (Phân công theo mức độ ưu tiên và khối lượng công việc): Thuật toán hỗ trợ tự động gợi ý hoặc phân công kỹ thuật viên phù hợp nhất dựa trên chuyên môn, trạng thái rảnh/bận hiện tại và độ khẩn cấp của yêu cầu (ticket).

Preventive Scheduling (Lên lịch phòng ngừa): Thuật toán tính toán thời gian và tự động sinh ra các yêu cầu bảo trì định kỳ (ví dụ: bảo trì máy lạnh mỗi 3 tháng, kiểm tra PCCC mỗi 6 tháng) dựa trên vòng đời thiết bị.

Hệ thống & Kiến trúc (System Architecture):

Role-Based Access Control (RBAC): Thiết kế kiến trúc phân quyền đa vai trò phức tạp và chặt chẽ (Admin, Facility Manager, Technician, Regular Staff) để bảo mật dữ liệu và chức năng tương ứng.

Automated Notification System: Xây dựng luồng thông báo tự động (qua Email hoặc hệ thống in-app) để cập nhật trạng thái sửa chữa ngay lập tức cho người báo cáo sự cố.

Công nghệ (Technologies):

Sử dụng Spring Boot / Java kết hợp với Spring Data JPA để thao tác và tối ưu hóa truy vấn cơ sở dữ liệu.

Nghiên cứu và tích hợp Quartz Scheduler hoặc Spring Batch để xử lý các tác vụ chạy ngầm tự động (Cron jobs cho bảo trì định kỳ).

Tích hợp Cloudinary hoặc AWS S3 để xử lý và lưu trữ hình ảnh hiện trường sự cố do nhân viên chụp lại.

3. Hướng dẫn cài đặt và chạy dự án (Installation & Setup Guide)
Prerequisites (Yêu cầu môi trường)

Java 17+ / Node.js (dành cho frontend nếu có)

MySQL / SQL Server

Tài khoản Cloudinary/S3 (Optional - dành cho tính năng upload ảnh)

Setup Steps (Các bước cài đặt)

Clone the repository:

Bash
git clone https://github.com/your-repo/office-maintenance-web.git
Cấu hình ORM tự động tạo bảng (auto-ddl) hoặc chạy các script SQL khởi tạo hệ thống danh mục thiết bị trong thư mục /database.

Cấu hình môi trường (Environment Configuration):

Mở file src/main/resources/application.properties (hoặc .yml).

Cấu hình thông tin kết nối Database và Email Server:

Properties
spring.datasource.url=jdbc:mysql://localhost:3306/maintenance_db
spring.datasource.username=root
spring.datasource.password=your_password

# Cấu hình gửi mail thông báo
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
Khởi chạy ứng dụng (Run the Application):

Mở terminal tại thư mục gốc của project backend và chạy lệnh:

Bash
mvn clean install
mvn spring-boot:run
Server backend sẽ khởi chạy tại: http://localhost:8080

4. Tài liệu API (API Documentation)
Sau khi ứng dụng khởi chạy thành công, có thể truy cập tài liệu API (Swagger UI) để xem chi tiết các endpoint quản lý thiết bị, ticket và user tại:
 http://localhost:8080/swagger-ui.html

5. Cấu trúc thư mục chính (Main Folder Structure)
 
6. src/main/java/com/maintenance/
├── config/           # Cấu hình Security (RBAC), Swagger, Scheduler

├── controller/       # Các API Endpoints (TicketController, DeviceController...)

├── dto/              # Các đối tượng truyền dữ liệu (Data Transfer Objects)

├── model/            # Thực thể Database (User, Equipment, MaintenanceTicket...)

├── repository/       # Giao tiếp với Database (Spring Data JPA)

└── service/          # Chứa logic nghiệp vụ (Thuật toán phân công, tính toán lịch bảo trì)
7. Thành viên nhóm (Contributors)

[Nguyễn Đức Thương ] - [DE190096] - Nhiệm vụ: Xử lý CSDL, API Đăng nhập / Backend

[Phạm Châu Vinh] - [DE190156] - Nhiệm vụ: Thuật toán Real-time Bidding (WebSocket) / Backend

[Phạm Văn Quyết ] - [DE190425] - Nhiệm vụ: Thiết kế giao diện / Frontend

[Phan Nguyễn ] - [DE191019] - Nhiệm vụ: Tích hợp thanh toán / API Sản phẩm

[Trương Quang Huy ] - [DE190381] - Nhiệm vụ: Viết tài liệu SRS, Tester & Quản lý dự án
Thiết lập Cơ sở dữ liệu (Database Setup):

Tạo một database trong MySQL với tên maintenance_db.
