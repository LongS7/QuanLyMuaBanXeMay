CREATE DATABASE QLBX
go


use QLBX
go

CREATE TABLE XeMay (
	maXe varchar(20) PRIMARY KEY,
	tenXe nvarchar(50),
	loaiXe nvarchar(30),
	hangXe nvarchar(30),
	dungTich int,
	mauXe nvarchar(30),
	nuocSX nvarchar(30),
	soLuongTon int,
	donGia money
)

CREATE TABLE NhanVien (
	ma varchar(20) PRIMARY KEY,
	hoTen nvarchar(50),
	gioiTinh bit,
	diaChi nvarchar(50),
	sdt varchar(20),
	email varchar(30),
	quanLyVien bit
)

CREATE TABLE KhachHang (
	ma varchar(20) PRIMARY KEY,
	hoTen nvarchar(50),
	gioiTinh bit,
	diaChi nvarchar(50),
	sdt varchar(20),
	email varchar(30)
) 

CREATE TABLE HoaDon (
	maHD varchar(20) PRIMARY KEY,
	maKH varchar(20) constraint fk_HD_KH foreign key references KhachHang(ma) on delete cascade,
	maNV varchar(20) constraint fk_HD_NV foreign key references NhanVien(ma) on delete cascade,
	ngayLap date
)

CREATE TABLE ChiTietHD(
	maHD varchar(20) constraint fk_CTHD_HD foreign key references HoaDon(maHD) on delete cascade,
	maXeMay varchar(20) constraint fk_CTHD_XM foreign key references XeMay(maXe) on delete cascade,
	soLuong int,
	donGia money
)

go


--Chèn dữ liệu

--Chèn bảng KhachHang

insert into KhachHang values
	('KH01', N'Nguyễn Văn Lâm', 1, N'Phường 3, Quận Bình Tân', '0386934153', null),
	('KH02', N'Hoàng Đình Chiến', 1, N'Phường 7, Quận Gò Vấp', '0986635476', null),
	('KH03', N'Hoàng Kim Thoa', 0, N'Phường 4, Quận Gò Vấp', '0367922251', null),
	('KH04', N'Trần Xuân Mạnh', 0, N'Phường 1, Quận Gò Vấp', '0367162251', 'xuanmanh@gmail.com'),
	('KH05', N'Kim Nguyên', 1, N'Phường 8, Quận Bình Thạnh', '0986478153', null),
	('KH06', N'Vũ Quang Khải', 1, N'Phường 8, Quận Bình Thạnh', '0932146789', 'quangkhai@yahoo.com'),
	('KH07', N'Bình Minh', 1, N'Quận 9', '0768394675', 'binhminh@gmail.com'),
	('KH08', N'Nông Vĩnh Kha', 1, N'Phường 6, Quận 12', '0658943156', null)
go


--Chền bảng NhanVien
insert into NhanVien values
	('NV01', N'Điểu Long', 1, N'Phường 4, Quận Gò Vấp', '0365978111', 'longdieu@gmail.com', 1),
	('NV02', N'Nguyễn Trung Hải', 1, N'Phường 4, Quận Gò Vấp', '098689746', 'trunghai@gmail.com', 1),
	('NV03', N'Hoàng Xuân Khang', 1, N'Phường 4, Quận Gò Vấp', '0367418965', 'xuankhang@gmail.com', 0),
	('NV04', N'Vũ Đình Khánh Đăng', 1, N'Phường 4, Quận Gò Vấp', '0931156448', 'khanhdang@gmail.com', 0)
go

--Chèn bảng XeMay
insert into XeMay values
	('XM01', N'JUPITER FI GP', N'Xe số', 'Yamaha', 114, N'Xanh', N'Nhật Bản', 13, 30000000),
	('XM02', N'JUPITER FI RC', N'Xe số', 'Yamaha', 114, N'Đen nhám', N'Nhật Bản', 11, 29400000),
	('XM03', N'GRANDE BLUE CORE HYBRID', N'Xe tay ga', 'Yamaha', 125, N'Trắng ngọc trai', N'Trung Quốc', 6, 50000000),
	('XM04', N'GRANDE BLUE CORE DELUX', N'Xe tay ga', 'Yamaha', 124, N'Xanh lá', N'Trung Quốc', 8, 41990000),
	('XM05', N'JANUS LIMITED', N'Xe tay ga', 'Yamaha', 125, N'Trắng xanh', N'Hàn Quốc', 3, 31990000),
	('XM06', N'NVX 155 ABS', N'Xe tay ga', 'Yamaha', 155, N'Đỏ', N'Nhật Bản', 16, 52240000)

go

--Chèn bảng HoaDon
insert into HoaDon values
	('HD01', 'KH01', 'NV01', GETDATE() - 10),
	('HD02', 'KH02', 'NV01', GETDATE() - 10),
	('HD03', 'KH03', 'NV01', GETDATE() - 8),
	('HD04', 'KH04', 'NV02', GETDATE() - 8),
	('HD05', 'KH05', 'NV01', GETDATE() - 8),
	('HD06', 'KH06', 'NV02', GETDATE() - 7),
	('HD07', 'KH07', 'NV02', GETDATE() - 7),
	('HD08', 'KH08', 'NV02', GETDATE() - 6),
	('HD09', 'KH03', 'NV01', GETDATE() - 5),
	('HD010', 'KH01', 'NV02', GETDATE() - 4)

go

--Chèn bảng ChiTietHD
insert into ChiTietHD values
	('HD01', 'XM03', 1, 50000000),
	('HD01', 'XM05', 1, 31990000),
	('HD02', 'XM05', 1, 31990000),
	('HD03', 'XM02', 1, 29400000),
	('HD04', 'XM02', 1, 29400000),
	('HD05', 'XM01', 2, 30000000),
	('HD06', 'XM04', 3, 41990000),
	('HD07', 'XM02', 1, 29400000),
	('HD08', 'XM06', 1, 52240000),
	('HD09', 'XM02', 3, 29400000),
	('HD010', 'XM01', 2, 30000000),
	('HD03', 'XM06', 1, 52240000),
	('HD07', 'XM03', 1, 50000000),
	('HD07', 'XM05', 1, 31990000)

go


use master
go

create login nv01
with password = '1530',
default_database = QLBX

create login nv02
with password = '1530',
default_database = QLBX

create login nv03
with password = '1530',
default_database = QLBX

create login nv04
with password = '1530',
default_database = QLBX
go

use QLBX
go

create user nv01
for login nv01

create user nv02
for login nv02

create user nv03
for login nv03

create user nv04
for login nv04
go

grant insert, select, delete, update
to nv01

grant insert, select, delete, update
to nv02

grant insert, select, delete, update
to nv03

grant insert, select, delete, update
to nv04

go

deny insert, update, delete
on NhanVien
to nv03, nv04
go


create function ThongKeTheoThang(@thang int, @nam int)
returns @return_value table (maNV varchar(20), tenNV nvarchar(50), soXeDaBan int, doanhThu money)
as
	begin
		insert into @return_value
			select a.ma, a.hoTen, SUM(c.soLuong), SUM(soLuong * donGia) from NhanVien a join HoaDon b on a.ma = b.maNV join ChiTietHD c on b.maHD = c.maHD
			where YEAR(ngayLap) = @nam and MONTH(ngayLap) = @thang
			group by a.ma, a.hoTen
		return
	end
go

create function ThongKeTheoNam(@nam int)
returns @return_value table (maNV varchar(20), tenNV nvarchar(50), soXeDaBan int, doanhThu money)
as
	begin
		insert into @return_value
			select a.ma, a.hoTen, SUM(c.soLuong), SUM(soLuong * donGia) from NhanVien a join HoaDon b on a.ma = b.maNV join ChiTietHD c on b.maHD = c.maHD
			where YEAR(ngayLap) = @nam
			group by a.ma, a.hoTen
		return
	end
go
