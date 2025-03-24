<<<<<<< HEAD
﻿--create database ASSslot1810
=======
﻿create database ASSslot1810

use ASSslot1810
>>>>>>> 9b0e173 (Cập nhật code)

CREATE TABLE Roles ( 
    RoleID INT PRIMARY KEY IDENTITY(1,1), 
    RoleName NVARCHAR(20) NOT NULL CHECK (RoleName IN ('Owner', 'Inspector', 'Station', 'Police', 'Admin')),
)

CREATE TABLE Users ( 
    UserID INT PRIMARY KEY IDENTITY(1,1), 
    FullName NVARCHAR(100) NOT NULL, 
    Email NVARCHAR(100) NOT NULL UNIQUE, 
    Password NVARCHAR(255) NOT NULL, 
    RoleID INT Not Null,
    Phone NVARCHAR(15) NOT NULL, 
    Address NVARCHAR(MAX) NOT NULL,
	FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
)

CREATE TABLE Brand ( 
    BrandID INT PRIMARY KEY IDENTITY(1,1),
    BrandName NVARCHAR(50) NOT NULL
)

CREATE TABLE Model ( 
    ModelID INT PRIMARY KEY IDENTITY(1,1),
    ModelName NVARCHAR(50) NOT NULL
)

CREATE TABLE Vehicles ( 
    VehicleID INT PRIMARY KEY IDENTITY(1,1), 
    OwnerID INT NOT NULL, 
    PlateNumber NVARCHAR(15) NOT NULL UNIQUE, 
    BrandID INT NOT NULL, 
    ModelID INT NOT NULL, 
    ManufactureYear INT NOT NULL, 
    EngineNumber NVARCHAR(100) NOT NULL, 
    FOREIGN KEY (OwnerID) REFERENCES Users(UserID),
    FOREIGN KEY (BrandID) REFERENCES Brand(BrandID),
    FOREIGN KEY (ModelID) REFERENCES Model(ModelID)
)

CREATE TABLE InspectionStations ( 
    StationID INT PRIMARY KEY IDENTITY(1,1), 
    Name NVARCHAR(100) NOT NULL, 
    Address NVARCHAR(MAX) NOT NULL, 
    Phone NVARCHAR(15) NOT NULL, 
    Email NVARCHAR(100) NOT NULL UNIQUE, 
    UserID INT, 
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
)

CREATE TABLE InspectionRecords (
    RecordID INT PRIMARY KEY IDENTITY(1,1), 
    VehicleID INT NOT NULL, 
    StationID INT NOT NULL, 
    InspectorID INt, 
    RegistrationDate DATETIME,
    InspectionDate DATETIME,
    Result NVARCHAR(10) NOT NULL CHECK (Result IN ('Pass', 'Fail', 'Pending', 'Testing')),
    CO2Emission DECIMAL(5,2) NOT NULL, 
    HCEmission DECIMAL(5,2) NOT NULL, 
    Comments NVARCHAR(MAX), 
    FOREIGN KEY (VehicleID) REFERENCES Vehicles(VehicleID), 
    FOREIGN KEY (StationID) REFERENCES InspectionStations(StationID),
    FOREIGN KEY (InspectorID) REFERENCES Users(UserID) 
)

CREATE TABLE Notifications ( 
    NotificationID INT PRIMARY KEY IDENTITY(1,1), 
    UserID INT NOT NULL, 
    Message NVARCHAR(MAX) NOT NULL,
    SentDate DATETIME DEFAULT CURRENT_TIMESTAMP, 
    IsRead BIT DEFAULT 0, 
    FOREIGN KEY (UserID) REFERENCES Users(UserID) 
)

CREATE TABLE Logs ( 
    LogID INT PRIMARY KEY IDENTITY(1,1), 
    UserID INT NOT NULL, 
    Action NVARCHAR(100) NOT NULL, 
    Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) 
)
INSERT INTO Roles (RoleName) VALUES
(N'Owner'),
(N'Inspector'),
(N'Station'),
(N'Police'),
(N'Admin');

INSERT INTO Users (FullName, Email, Password, RoleID, Phone, Address) VALUES
(N'Nguyễn Văn A', N'nguyenvana@example.com', 'password1', 1, N'0901234567', N'Hà Nội'),
(N'Trần Thị B', N'tranthib@example.com', 'password2', 2, N'0912345678', N'Hồ Chí Minh'),
(N'Lê Văn C', N'levanc@example.com', 'password3', 3, N'0923456789', N'Đà Nẵng'),
(N'Phạm Quốc D', N'phamquocd@example.com', 'password4', 4, N'0934567890', N'Cần Thơ'),
(N'Đặng Hoàng E', N'danghoange@example.com', 'password5', 5, N'0945678901', N'Hải Phòng'),
(N'Đặng f', N'dangf@example.com', 'password6', 3, N'0945678901', N'Hải Dương');


INSERT INTO Brand (BrandName) VALUES
(N'Toyota'),
(N'Honda'),
(N'Ford'),
(N'Hyundai'),
(N'Mercedes');

INSERT INTO Model (ModelName) VALUES
(N'Camry'),
(N'Civic'),
(N'Ranger'),
(N'Santafe'),
(N'C-Class');

INSERT INTO Vehicles (OwnerID, PlateNumber, BrandID, ModelID, ManufactureYear, EngineNumber) VALUES
(1, N'30A-12345', 1, 1, 2020, N'ENG12345'),
(1, N'29B-67890', 2, 2, 2018, N'ENG67890'),
(2, N'51C-24680', 3, 3, 2019, N'ENG24680'),
(3, N'43D-13579', 4, 4, 2021, N'ENG13579'),
(4, N'59E-11223', 5, 5, 2022, N'ENG11223');


INSERT INTO InspectionStations (Name, Address, Phone, Email, UserID) VALUES
(N'Trung tâm đăng kiểm Hà Nội', N'123 Đường ABC, Hà Nội', N'0987654321', N'dangkiem_hn@example.com', 3),
(N'Trung tâm đăng kiểm TP.HCM', N'456 Đường XYZ, Hồ Chí Minh', N'0976543210', N'dangkiem_hcm@example.com', 6);

INSERT INTO InspectionRecords (VehicleID, StationID, InspectorID, RegistrationDate, InspectionDate, Result, CO2Emission, HCEmission, Comments) VALUES
(1, 1, 2, '2024-02-01', '2024-02-05', N'Pass', 1.20, 0.80, N'Xe đạt tiêu chuẩn khí thải'),
(2, 1, 2, '2024-02-10', '2024-02-12', N'Fail', 2.50, 1.50, N'Lượng khí thải vượt mức cho phép'),
(3, 2, 2, '2024-02-15', '2024-02-18', N'Pending', 0.00, 0.00, N'Chờ kiểm định'),
(4, 2, 2, '2024-02-20', '2024-02-22', N'Testing', 0.00, 0.00, N'Đang trong quá trình kiểm tra');

INSERT INTO Notifications (UserID, Message) VALUES
(1, N'Xe của bạn đã đăng ký kiểm định thành công.'),
(2, N'Bạn có một cuộc hẹn kiểm định xe vào ngày 2024-02-12.');

INSERT INTO Logs (UserID, Action) VALUES 
(1, N'Đăng ký kiểm định xe'),
(2, N'Thực hiện kiểm định xe'),
<<<<<<< HEAD
(3, N'Cập nhật trạng thái kiểm định');

select *from InspectionStations
where Userid is null

select * from Users
=======
(3, N'Cập nhật trạng thái kiểm định');
>>>>>>> 9b0e173 (Cập nhật code)
