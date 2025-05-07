# Tổng quan dự án

**Mục đích sử dụng thư mục `modelTemp`:**
Thư mục `modelTemp` chỉ dùng để tạo database ban đầu. Sau khi tạo xong, bạn cần **di chuyển các model cần dùng sang đúng thư mục** để sử dụng trong code chính.

### Hướng dẫn chạy dự án lần đầu:

1. Mở file `application.yml`
2. Chỉnh lại 2 dòng sau:

   ```yaml
   spring:
     jpa:
       hibernate:
         ddl-auto: update
     flyway:
       enabled: false
   ```

### Chạy lần thứ hai:

1. Tiếp tục mở lại `application.yml`
2. Chỉnh lại như sau:

   ```yaml
   spring:
     jpa:
       hibernate:
         ddl-auto: validate
     flyway:
       enabled: true  # lấy dữ liệu giả (data fake)
   ```

---

## Công nghệ sử dụng

Dự án sử dụng các công nghệ sau:

* **PostgreSQL 16**
* **Java 21**
* **Spring Boot 3.2**

---

## Cách chạy dự án

1. **Build dự án**:

   ```bash
   npm install
   mvn clean install
   ```

2. **Chạy ứng dụng Spring Boot**:

   ```bash
   mvn spring-boot:run
   ```

---

## Cấu trúc dự án

Dự án gồm **11 module** chính:

* **Booking** (Đặt chỗ)
* **Bot**
* **Delivery** (Giao hàng)
* **EatIn** (Ăn tại chỗ)
* **Kitchen** (Bếp)
* **Menu** (Thực đơn)
* **Report** (Báo cáo)
* **Schedule** (Lịch trình)
* **TakeOut** (Mang đi)
* **User** (Đã hoàn thành khung cơ bản)

### Thư viện dùng chung:

* **Library**: Chứa các class và tiện ích được dùng chung giữa các module.

---

### Cấu trúc mỗi module (ví dụ: module `User`)

Mỗi module đều có cấu trúc giống nhau, gồm:

#### `api`

* `dto`: Định nghĩa các lớp DTO (Data Transfer Object) – dùng để trao đổi dữ liệu giữa các module.
* `event`: Định nghĩa các sự kiện để truyền dữ liệu bất đồng bộ giữa các module.
* `mapper`: Chuyển đổi giữa DTO và Entity.
* `service`: Khai báo các interface cho phần xử lý nghiệp vụ (business logic), module khác sẽ gọi qua đây.

#### `common`

* Tạm thời chứa các mã trạng thái và thông báo hệ thống (`Api___Message.java`, ...)

#### `controller`

* Giao tiếp với client (API), xử lý các request gửi từ phía frontend.

#### `model`

* Chứa các Entity tương ứng với bảng dữ liệu trong database.

#### `repository`

* Giao tiếp với database (CRUD, query...).

#### `service`

* Chứa phần hiện thực của `service` (logic xử lý chính).

---

### Giao tiếp giữa các module

* **Giao tiếp đồng bộ (Synchronous)**: Các module gọi lẫn nhau thông qua các interface của `service`.

---

## Cấu trúc thư viện chung (`Library`)

Module `Library` là nơi chứa các thành phần dùng chung trong toàn bộ hệ thống, gồm:

* **dto**: Các lớp DTO cơ bản như `ListRequest`, dùng lặp lại ở nhiều module.
* **enumeration**: Chứa các Enum (trạng thái, mã cố định...). Khi cần Enum mới, thêm vào đây.
* **common**: Các class tiện ích chung như `Response`, `PageResponse`,...
* **util**: Class hỗ trợ như `DateUtil`, `StringUtil`, v.v.
* **configuration**: Các thiết lập cấu hình cho toàn ứng dụng.
* **security**: Thiết lập bảo mật và các class liên quan đến an ninh hệ thống.
* **exception**: Định nghĩa các loại exception tuỳ chỉnh để xử lý lỗi rõ ràng.
