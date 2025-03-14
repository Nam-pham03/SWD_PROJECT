USE [BookShopSystem]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 11/3/2024 3:52:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[account_id] [int] IDENTITY(1,1) NOT NULL,
	[email] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[fullname] [nvarchar](50) NULL,
	[phone] [nvarchar](50) NULL,
	[status_acc] [int] NULL,
	[address] [nvarchar](50) NULL,
	[dob] [date] NULL,
	[gender] [int] NULL,
	[token] [nvarchar](max) NULL,
	[role] [int] NULL,
	[update_time] [time](7) NULL,
	[create_time] [time](7) NULL,
	[update_by] [nvarchar](max) NULL,
	[create_by] [nvarchar](max) NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[account_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 11/3/2024 3:52:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[category_id] [int] IDENTITY(1,1) NOT NULL,
	[category_name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 11/3/2024 3:52:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[customer_id] [int] IDENTITY(1,1) NOT NULL,
	[account_id] [int] NULL,
	[point] [int] NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[customer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 11/3/2024 3:52:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[order_id] [int] NOT NULL,
	[order_date] [date] NULL,
	[note] [nvarchar](50) NULL,
	[totalPrice] [int] NULL,
	[status_order] [int] NULL,
	[statuts_payment] [int] NULL,
	[customer_id] [int] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order_detail]    Script Date: 11/3/2024 3:52:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order_detail](
	[detail_id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NULL,
	[order_id] [int] NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_Order_detail] PRIMARY KEY CLUSTERED 
(
	[detail_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 11/3/2024 3:52:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[product_id] [int] IDENTITY(1,1) NOT NULL,
	[price] [int] NULL,
	[img] [nvarchar](50) NULL,
	[description] [nvarchar](max) NULL,
	[product_name] [nvarchar](50) NULL,
	[release_date] [date] NULL,
	[author] [nvarchar](50) NULL,
	[quantity] [int] NULL,
	[status_pro] [int] NULL,
	[create_by] [nvarchar](max) NULL,
	[update_by] [nvarchar](max) NULL,
	[update_time] [time](7) NULL,
	[create_time] [time](7) NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product_Category]    Script Date: 11/3/2024 3:52:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product_Category](
	[product_id] [int] NOT NULL,
	[category_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC,
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product_Feedback]    Script Date: 11/3/2024 3:52:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product_Feedback](
	[feedback_id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NULL,
	[star] [int] NULL,
	[comment] [text] NULL,
	[customer_id] [int] NULL,
 CONSTRAINT [PK_Product_Feedback] PRIMARY KEY CLUSTERED 
(
	[feedback_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Shipping]    Script Date: 11/3/2024 3:52:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Shipping](
	[ship_id] [int] NOT NULL,
	[address] [nvarchar](50) NULL,
	[status_ship] [int] NULL,
	[status_payment] [int] NULL,
	[staff_id] [int] NULL,
 CONSTRAINT [PK_Shipping] PRIMARY KEY CLUSTERED 
(
	[ship_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Staff]    Script Date: 11/3/2024 3:52:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Staff](
	[staff_id] [int] IDENTITY(1,1) NOT NULL,
	[account_id] [int] NULL,
	[role] [int] NULL,
 CONSTRAINT [PK_Staff] PRIMARY KEY CLUSTERED 
(
	[staff_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (1, N'nampc257@gmail.com', N'96e79218965eb72c92a549dd5a330112', N'Nam ', N'0392417166', 0, N'Hà Nội', CAST(N'2003-10-17' AS Date), 0, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (2, N'nampqhe173479@fpt.edu.vn', N'e10adc3949ba59abbe56e057f20f883e', N'Phạm Quang Nam', N'0987023017', 1, N'Hà Nội', CAST(N'2003-10-17' AS Date), 0, NULL, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (3, N'sale@gmail.com', N'e10adc3949ba59abbe56e057f20f883e', N'Lê Thị Minh Nguyêt', N'0987023016', 1, N'Hà Nội', CAST(N'2003-10-17' AS Date), 0, NULL, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (4, N'manager@gmail.com', N'e10adc3949ba59abbe56e057f20f883e', N'Đỗ Minh Quân', N'0392417166', 1, N'Hà Nội', CAST(N'2003-10-17' AS Date), 0, NULL, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (5, N'shipper@gmail.com', N'e10adc3949ba59abbe56e057f20f883e', N'Phùng Tuấn Đạt', N'0392417166', 1, N'Hà Nội', CAST(N'2003-10-17' AS Date), 0, NULL, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (6, N'phungdat@gmail.com', N'e10adc3949ba59abbe56e057f20f883e', N'Phùng Tuấn ', N'0392417166', 1, N'Hà Nội', CAST(N'2003-03-07' AS Date), 0, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (7, N'tuandat@gmail.com', N'e10adc3949ba59abbe56e057f20f883e', N'Tuan đat', N'0392417166', 1, N'Hà Nội', CAST(N'2003-02-01' AS Date), 0, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (8, N'anhpham@gmail.com', N'e10adc3949ba59abbe56e057f20f883e', N'Phạm Trung Anh', N'0392417166', 1, N'Hà Nội', CAST(N'2003-10-23' AS Date), 0, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (9, N'minhquan@gmail.com', N'96e79218965eb72c92a549dd5a330112', N'Phạm Trung Anh', N'0392417166', 1, N'Hà Nội', CAST(N'2003-10-26' AS Date), 0, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (10, N'minhnguyet@gmail.com', N'e10adc3949ba59abbe56e057f20f883e', N'Phạm Trung Anh', N'0392417166', 0, N'nampc257@gmail.com', CAST(N'2003-10-23' AS Date), 1, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (11, N'nam@gmail.com', N'211c9bfe9f65bb593128ca11ce792e41', N'Phùng Tuấn Đạt', N'0392417166', 1, N'Hà Nội', CAST(N'2003-11-15' AS Date), 1, NULL, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (12, N'mai@gmail.com', N'211c9bfe9f65bb593128ca11ce792e41', N'Phùng Tuấn Đạt', N'0392417166', 1, N'Hà Nội', CAST(N'2003-11-19' AS Date), 0, NULL, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (13, N'nguyet@gmail.com', N'211c9bfe9f65bb593128ca11ce792e41', N'Phùng Tuấn Đạt', N'0392417166', 0, N'Hà Nội', CAST(N'2024-11-15' AS Date), 0, NULL, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (14, N'dat@gmail.com', N'211c9bfe9f65bb593128ca11ce792e41', N'Phùng Tuấn Đạt', N'0392417166', 1, N'Hà Nội', CAST(N'2024-11-21' AS Date), 0, NULL, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Account] ([account_id], [email], [password], [fullname], [phone], [status_acc], [address], [dob], [gender], [token], [role], [update_time], [create_time], [update_by], [create_by]) VALUES (15, N'ducquang@gmail.com', N'e10adc3949ba59abbe56e057f20f883e', N'Nam Phạm', N'0039417166', 1, N'Hà Nội', CAST(N'2003-11-21' AS Date), 1, NULL, 0, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([category_id], [category_name]) VALUES (1, N'Văn học')
INSERT [dbo].[Category] ([category_id], [category_name]) VALUES (2, N'Kinh tế')
INSERT [dbo].[Category] ([category_id], [category_name]) VALUES (3, N'Tâm Lý - Kĩ năng sống')
INSERT [dbo].[Category] ([category_id], [category_name]) VALUES (4, N'Nuôi dạy con')
INSERT [dbo].[Category] ([category_id], [category_name]) VALUES (5, N'sách thiếu nhi')
INSERT [dbo].[Category] ([category_id], [category_name]) VALUES (6, N'Tiểu sử - Hồi kí')
INSERT [dbo].[Category] ([category_id], [category_name]) VALUES (7, N'Giáo Khoa - Tham Khảo')
INSERT [dbo].[Category] ([category_id], [category_name]) VALUES (8, N'Sách học Ngoại Ngữ')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([customer_id], [account_id], [point]) VALUES (1, 1, 0)
INSERT [dbo].[Customer] ([customer_id], [account_id], [point]) VALUES (2, 6, 0)
INSERT [dbo].[Customer] ([customer_id], [account_id], [point]) VALUES (3, 7, 0)
INSERT [dbo].[Customer] ([customer_id], [account_id], [point]) VALUES (4, 8, 0)
INSERT [dbo].[Customer] ([customer_id], [account_id], [point]) VALUES (5, 9, 0)
INSERT [dbo].[Customer] ([customer_id], [account_id], [point]) VALUES (6, 10, 0)
INSERT [dbo].[Customer] ([customer_id], [account_id], [point]) VALUES (7, 15, 0)
SET IDENTITY_INSERT [dbo].[Customer] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([product_id], [price], [img], [description], [product_name], [release_date], [author], [quantity], [status_pro], [create_by], [update_by], [update_time], [create_time]) VALUES (1, 11, N'./img/_lbccn1_1.jpg', N'“Đây là bản chất con người” sẽ là ngọn đèn giúp bạn soi tỏ lòng mình giữa một thế gian đầy biến động. Đến gần nhân tính không phải nhìn thấu tâm can người khác hay điều khiển một mối quan hệ nào đó, mà chính là thấu hiểu và biết khống chế bản thân tốt hơn. Hóa ra, đến cuối cùng, chúng ta vẫn chẳng kiểm soát được gì trên đời này ngoài chính mình.', N'Bản Chất Của Người', CAST(N'2019-10-15' AS Date), N'Vương Tâm Ngạo', 0, 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([product_id], [price], [img], [description], [product_name], [release_date], [author], [quantity], [status_pro], [create_by], [update_by], [update_time], [create_time]) VALUES (2, 10, N'./img/image_237214.jpg', N'Suốt sự nghiệp viết văn gần ba mươi năm, với 3 tập truyện ngắn, 6 tiểu thuyết và 1 tập thơ, Han Kang đã giành được nhiều giải thưởng văn học danh giá cả trong và ngoài nước, trở thành một trong những nhà văn quan trọng nhất của văn học Hàn Quốc hiện đại. Trong đó nổi bật nhất là giải Man Booker International năm 2016 dành cho tác phẩm Người ăn chay. Năm 2018, cô tiếp tục lọt vào danh sách đề cử của giải thưởng này với tiểu thuyết giàu tính tự thuật Trắng.', N'Trắng', CAST(N'2021-12-31' AS Date), N'	 Han Kang', 0, 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([product_id], [price], [img], [description], [product_name], [release_date], [author], [quantity], [status_pro], [create_by], [update_by], [update_time], [create_time]) VALUES (3, 10, N'./img/image_195509_1_41170.jpg', N'Dựa trên nền tảng của sử thi Iliad, câu chuyện về cuộc chiến thành Troy vĩ đại đã được Madeline Miller kể lại với tiết tấu dồn dập, lôi cuốn, và không kém phần xúc động, đánh dấu sự khởi đầu của một sự nghiệp rực rỡ.

Trường Ca Achilles đã đoạt giải Orange năm 2012 và luôn nằm trong top các sách bán chạy của tờ New York Times.', N'Trường Ca Achilles', CAST(N'2020-10-10' AS Date), N'Madeline Miller', 100, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([product_id], [price], [img], [description], [product_name], [release_date], [author], [quantity], [status_pro], [create_by], [update_by], [update_time], [create_time]) VALUES (4, 10, N'./img/9786043651591_1.jpg', N'Mỗi một câu chuyện là một viên charm lấp lánh, kiêu kỳ, có sức hút mạnh mẽ đối với một người trẻ như tôi luôn tò mò với những điều dung dị trong cuộc sống. Tôi âm thầm nhặt những viên charm ấy về, kết thành sợi Pandora cho chính mình. Lén ở đây không phải là một cái gì đó vụng trộm, âm thầm sợ người khác phát hiện. Mà nó là lặng lẽ. Tôi lặng lẽ nghe, lặng lẽ quan sát, lặng lẽ đi tìm và lặng lẽ viết nên quyển sách này. Tôi vẫn thích dùng từ Lén hơn, vì đơn giản, tôi thấy bản thân mình trong đó.', N'Lén Nhặt Chuyện Đời', CAST(N'2024-10-20' AS Date), N'Mộc Trầm', 100, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([product_id], [price], [img], [description], [product_name], [release_date], [author], [quantity], [status_pro], [create_by], [update_by], [update_time], [create_time]) VALUES (5, 10, N'./img/8935325011559.jpg', N'Đám trẻ ở đại dương đen là lời độc thoại và đối thoại của những đứa trẻ ở đại dương đen, nơi từng lớp sóng của nỗi buồn và tuyệt vọng không ngừng cuộn trào, lúc âm ỉ, khi dữ dội. Những đứa trẻ ấy phải vật lộn trong những góc tối tâm lý, với sự u uất đè nén từ tổn thương khi không được sinh ra trong một gia đình toàn vẹn, ấm êm, khi phải mang trên đôi vai non dại những gánh nặng không tưởng.', N'Đám Trẻ Ở Đại Dương Đen', CAST(N'2023-10-22' AS Date), N'	 Châu Sa Đáy Mắt', 10, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([product_id], [price], [img], [description], [product_name], [release_date], [author], [quantity], [status_pro], [create_by], [update_by], [update_time], [create_time]) VALUES (6, 10, N'./img/8934974182375.jpg', N'Mang chất trào lộng duyên dáng kiểu Bắc Âu nhưng cũng tràn đầy tính nhân văn, cuốn sách trở thành một hiện tượng toàn cầu với gần 3 triệu bản in được bán ra, và được dịch sang 40 ngôn ngữ. Bộ phim cùng tên chuyển thể từ cuốn tiểu thuyết đã được đề cử ở hạng mục phim nói tiếng nước ngoài hay nhất tại Oscar 2017.', N'Người Đàn Ông Mang Tên OVE (Tái Bản)', CAST(N'2022-02-06' AS Date), N'	 Fredrik Backman', 10, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([product_id], [price], [img], [description], [product_name], [release_date], [author], [quantity], [status_pro], [create_by], [update_by], [update_time], [create_time]) VALUES (7, 10, N'./img/image_195509_1_29716.jpg', N'NGÀY XƯA CÓ MỘT CHUYỆN TÌNH là tác phẩm mới tinh thứ 2 trong năm 2016 của nhà văn Nguyễn Nhật Ánh dài hơn 300 trang, được coi là tập tiếp theo của tập truyện Mắt biếc. Có một tình yêu dữ dội, với em,  của một người yêu em hơn chính bản thân mình - là anh.', N'Ngày Xưa Có Một Chuyện Tình (Tái Bản 2019)', CAST(N'2019-07-03' AS Date), N'Nguyễn Nhật Ánh', 10, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([product_id], [price], [img], [description], [product_name], [release_date], [author], [quantity], [status_pro], [create_by], [update_by], [update_time], [create_time]) VALUES (8, 10, N'./img/b_a-1-25-_-_m_1.jpg', N'“Mỗi một cột mốc lại có những người dẫn đường khác nhau. Mắt xích ở khu vực này khi đã bàn giao người cho chân rết ở nơi khác là hết nhiệm vụ, đừng mong bọn họ sẽ quay lại giải quyết hay giúp đỡ bất cứ điều gì. Nhiều người gọi chúng là những kẻ “lái người”. Kể cả những người vượt biên có chết trước mặt thì những kẻ đó cũng phải rời đi ngay lập tức để tránh liên lụy. Nói một cách đơn giản hơn, người trong đường dây chỉ đưa chúng tôi đến các địa điểm trên cung đường như đã định. Còn việc chúng tôi có đến được hay không thì phải tùy thuộc vào ý trời.”', N'25 Độ Âm', CAST(N'2024-10-17' AS Date), N'Thảo Trang', 0, 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([product_id], [price], [img], [description], [product_name], [release_date], [author], [quantity], [status_pro], [create_by], [update_by], [update_time], [create_time]) VALUES (235, 10, N'./img/image_231202.jpg', N'Hạnh phúc không phải là thứ được định đoạt bằng tiền. Hạnh phúc phải được định đoạt bằng tâm thế của mỗi chúng ta.', N'Người Bà Tài Giỏi Vùng Saga', CAST(N'2021-10-01' AS Date), N'	 Yoshichi Shimada', 0, 2, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([product_id], [price], [img], [description], [product_name], [release_date], [author], [quantity], [status_pro], [create_by], [update_by], [update_time], [create_time]) VALUES (237, 10, N'./img/Ảnh chụp màn hình 2024-01-07 141148.png', N'ok', N'Phùng Tuấn Đạt', CAST(N'2024-11-11' AS Date), N'hg', 2, 0, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (1, 1)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (1, 6)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (2, 1)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (2, 3)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (3, 1)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (4, 1)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (4, 3)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (4, 5)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (5, 1)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (5, 4)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (5, 5)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (6, 1)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (7, 1)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (8, 1)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (235, 1)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (237, 1)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (237, 2)
INSERT [dbo].[Product_Category] ([product_id], [category_id]) VALUES (237, 3)
GO
SET IDENTITY_INSERT [dbo].[Staff] ON 

INSERT [dbo].[Staff] ([staff_id], [account_id], [role]) VALUES (1, 2, 0)
INSERT [dbo].[Staff] ([staff_id], [account_id], [role]) VALUES (2, 3, 3)
INSERT [dbo].[Staff] ([staff_id], [account_id], [role]) VALUES (3, 4, 1)
INSERT [dbo].[Staff] ([staff_id], [account_id], [role]) VALUES (4, 5, 2)
INSERT [dbo].[Staff] ([staff_id], [account_id], [role]) VALUES (5, 11, 3)
INSERT [dbo].[Staff] ([staff_id], [account_id], [role]) VALUES (6, 12, 3)
INSERT [dbo].[Staff] ([staff_id], [account_id], [role]) VALUES (7, 13, 3)
INSERT [dbo].[Staff] ([staff_id], [account_id], [role]) VALUES (8, 14, 3)
SET IDENTITY_INSERT [dbo].[Staff] OFF
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD  CONSTRAINT [FK_Customer_Account] FOREIGN KEY([account_id])
REFERENCES [dbo].[Account] ([account_id])
GO
ALTER TABLE [dbo].[Customer] CHECK CONSTRAINT [FK_Customer_Account]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Customer] FOREIGN KEY([customer_id])
REFERENCES [dbo].[Customer] ([customer_id])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Customer]
GO
ALTER TABLE [dbo].[Order_detail]  WITH CHECK ADD  CONSTRAINT [FK_Order_detail_Order] FOREIGN KEY([order_id])
REFERENCES [dbo].[Order] ([order_id])
GO
ALTER TABLE [dbo].[Order_detail] CHECK CONSTRAINT [FK_Order_detail_Order]
GO
ALTER TABLE [dbo].[Order_detail]  WITH CHECK ADD  CONSTRAINT [FK_Order_detail_Product] FOREIGN KEY([product_id])
REFERENCES [dbo].[Product] ([product_id])
GO
ALTER TABLE [dbo].[Order_detail] CHECK CONSTRAINT [FK_Order_detail_Product]
GO
ALTER TABLE [dbo].[Product_Category]  WITH CHECK ADD  CONSTRAINT [FK__Product_C__categ__4AB81AF0] FOREIGN KEY([category_id])
REFERENCES [dbo].[Category] ([category_id])
GO
ALTER TABLE [dbo].[Product_Category] CHECK CONSTRAINT [FK__Product_C__categ__4AB81AF0]
GO
ALTER TABLE [dbo].[Product_Category]  WITH CHECK ADD  CONSTRAINT [FK__Product_C__produ__49C3F6B7] FOREIGN KEY([product_id])
REFERENCES [dbo].[Product] ([product_id])
GO
ALTER TABLE [dbo].[Product_Category] CHECK CONSTRAINT [FK__Product_C__produ__49C3F6B7]
GO
ALTER TABLE [dbo].[Product_Feedback]  WITH CHECK ADD  CONSTRAINT [FK_Product_Feedback_Customer] FOREIGN KEY([customer_id])
REFERENCES [dbo].[Customer] ([customer_id])
GO
ALTER TABLE [dbo].[Product_Feedback] CHECK CONSTRAINT [FK_Product_Feedback_Customer]
GO
ALTER TABLE [dbo].[Product_Feedback]  WITH CHECK ADD  CONSTRAINT [FK_Product_Feedback_Product] FOREIGN KEY([product_id])
REFERENCES [dbo].[Product] ([product_id])
GO
ALTER TABLE [dbo].[Product_Feedback] CHECK CONSTRAINT [FK_Product_Feedback_Product]
GO
ALTER TABLE [dbo].[Shipping]  WITH CHECK ADD  CONSTRAINT [FK_Shipping_Order] FOREIGN KEY([ship_id])
REFERENCES [dbo].[Order] ([order_id])
GO
ALTER TABLE [dbo].[Shipping] CHECK CONSTRAINT [FK_Shipping_Order]
GO
ALTER TABLE [dbo].[Shipping]  WITH CHECK ADD  CONSTRAINT [FK_Shipping_Staff] FOREIGN KEY([staff_id])
REFERENCES [dbo].[Staff] ([staff_id])
GO
ALTER TABLE [dbo].[Shipping] CHECK CONSTRAINT [FK_Shipping_Staff]
GO
ALTER TABLE [dbo].[Staff]  WITH CHECK ADD  CONSTRAINT [FK_Staff_Account] FOREIGN KEY([account_id])
REFERENCES [dbo].[Account] ([account_id])
GO
ALTER TABLE [dbo].[Staff] CHECK CONSTRAINT [FK_Staff_Account]
GO
