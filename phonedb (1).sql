USE [master]
GO
/****** Object:  Database [phonedb]    Script Date: 5/31/2023 10:19:40 PM ******/
CREATE DATABASE [phonedb]
 CONTAINMENT = NONE
GO
ALTER DATABASE [phonedb] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [phonedb].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [phonedb] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [phonedb] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [phonedb] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [phonedb] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [phonedb] SET ARITHABORT OFF 
GO
ALTER DATABASE [phonedb] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [phonedb] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [phonedb] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [phonedb] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [phonedb] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [phonedb] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [phonedb] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [phonedb] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [phonedb] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [phonedb] SET  DISABLE_BROKER 
GO
ALTER DATABASE [phonedb] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [phonedb] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [phonedb] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [phonedb] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [phonedb] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [phonedb] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [phonedb] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [phonedb] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [phonedb] SET  MULTI_USER 
GO
ALTER DATABASE [phonedb] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [phonedb] SET DB_CHAINING OFF 
GO
ALTER DATABASE [phonedb] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [phonedb] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [phonedb] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [phonedb] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [phonedb] SET QUERY_STORE = OFF
GO
USE [phonedb]
GO
/****** Object:  Table [dbo].[cart_items]    Script Date: 5/31/2023 10:19:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart_items](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NULL,
	[prod_id] [int] NULL,
	[qty] [int] NULL,
	[price] [float] NULL,
	[name] [varchar](255) NULL,
 CONSTRAINT [PK_carts] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[categories]    Script Date: 5/31/2023 10:19:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categories](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NULL,
	[description] [text] NULL,
	[status] [tinyint] NULL,
	[image] [nvarchar](255) NULL,
 CONSTRAINT [PK_categories] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_details]    Script Date: 5/31/2023 10:19:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_details](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [varchar](255) NOT NULL,
	[prod_id] [int] NOT NULL,
	[qty] [int] NOT NULL,
	[price] [float] NOT NULL,
	[created_at] [varchar](255) NULL,
	[updated_at] [varchar](255) NULL,
 CONSTRAINT [PK_order_details] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 5/31/2023 10:19:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[id] [varchar](255) NOT NULL,
	[user_id] [int] NOT NULL,
	[name] [varchar](255) NULL,
	[email] [varchar](255) NOT NULL,
	[phone] [varchar](255) NOT NULL,
	[address] [varchar](255) NOT NULL,
	[payment_mode] [varchar](255) NULL,
	[status] [tinyint] NOT NULL,
	[total_price] [float] NOT NULL,
 CONSTRAINT [PK_order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[products]    Script Date: 5/31/2023 10:19:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[products](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cate_id] [int] NOT NULL,
	[name] [varchar](255) NULL,
	[description] [text] NULL,
	[og_price] [float] NULL,
	[selling_price] [float] NULL,
	[image] [nvarchar](255) NULL,
	[qty] [int] NULL,
	[status] [tinyint] NULL,
	[trending] [tinyint] NULL,
 CONSTRAINT [PK_products] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rate_prod]    Script Date: 5/31/2023 10:19:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rate_prod](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NULL,
	[prod_id] [int] NULL,
	[stars_rated] [int] NULL,
	[created_at] [timestamp] NULL,
	[updated_at] [time](7) NULL,
 CONSTRAINT [PK_rate_prod] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[reviews]    Script Date: 5/31/2023 10:19:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[reviews](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NULL,
	[prod_id] [int] NULL,
	[user_review] [text] NULL,
	[created_at] [date] NULL,
	[updated_at] [date] NULL,
 CONSTRAINT [PK_reviews] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 5/31/2023 10:19:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NOT NULL,
	[email] [varchar](255) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[phone] [varchar](255) NULL,
	[address] [varchar](255) NULL,
	[role] [nvarchar](255) NULL,
	[state] [varchar](255) NULL,
	[created_at] [varchar](255) NULL,
	[updated_at] [varchar](255) NULL,
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[categories] ON 

INSERT [dbo].[categories] ([id], [name], [description], [status], [image]) VALUES (20, N'Head Phone', N'Head Phone', 1, N'group_172_2.webp')
INSERT [dbo].[categories] ([id], [name], [description], [status], [image]) VALUES (21, N'Mobile Phone', N'phone', 1, N'14_1_9_2_9.webp')
INSERT [dbo].[categories] ([id], [name], [description], [status], [image]) VALUES (31, N'Charg', N'Charging and cable', 1, N'frame_1_10_.webp')
INSERT [dbo].[categories] ([id], [name], [description], [status], [image]) VALUES (35, N'Watch', N'watch', 1, N'apple_gps_7_.webp')
INSERT [dbo].[categories] ([id], [name], [description], [status], [image]) VALUES (36, N'Speaker', N'speaker', 1, N'group_211_2_1.webp')
SET IDENTITY_INSERT [dbo].[categories] OFF
GO
SET IDENTITY_INSERT [dbo].[order_details] ON 

INSERT [dbo].[order_details] ([id], [order_id], [prod_id], [qty], [price], [created_at], [updated_at]) VALUES (1, N'1', 23, 10, 2000, N'May 27 2023 12:00AM', NULL)
INSERT [dbo].[order_details] ([id], [order_id], [prod_id], [qty], [price], [created_at], [updated_at]) VALUES (2, N'6a2825ef-2ed9-4b92-9a12-eeb704da0791', 27, 12, 12345, N'2023-05-20T18:04:14.534007200', NULL)
INSERT [dbo].[order_details] ([id], [order_id], [prod_id], [qty], [price], [created_at], [updated_at]) VALUES (3, N'b4381fc5-62ac-4ae8-ae02-5c6ecaaf2827', 27, 1, 12345, N'2023-05-31T14:07:59.791702700', N'')
INSERT [dbo].[order_details] ([id], [order_id], [prod_id], [qty], [price], [created_at], [updated_at]) VALUES (4, N'b4381fc5-62ac-4ae8-ae02-5c6ecaaf2827', 30, 1, 123, N'2023-05-20T14:07:59.836425800', N'')
INSERT [dbo].[order_details] ([id], [order_id], [prod_id], [qty], [price], [created_at], [updated_at]) VALUES (5, N'b9a5810d-f7ad-4233-92db-35581b60ee95', 23, 6, 123124, N'2023-05-31T15:08:05.423894', N'')
INSERT [dbo].[order_details] ([id], [order_id], [prod_id], [qty], [price], [created_at], [updated_at]) VALUES (6, N'74429dc1-706f-4450-9579-feb0561d21ac', 23, 1, 2790000, N'2023-05-31T21:44:27.140368700', N'')
INSERT [dbo].[order_details] ([id], [order_id], [prod_id], [qty], [price], [created_at], [updated_at]) VALUES (7, N'74429dc1-706f-4450-9579-feb0561d21ac', 30, 1, 16690000, N'2023-05-31T21:44:27.189370800', N'')
INSERT [dbo].[order_details] ([id], [order_id], [prod_id], [qty], [price], [created_at], [updated_at]) VALUES (8, N'a3016e26-519f-40e7-9f31-81dccdad01e1', 28, 1, 26890000, N'2023-05-31T21:54:30.407036300', N'')
INSERT [dbo].[order_details] ([id], [order_id], [prod_id], [qty], [price], [created_at], [updated_at]) VALUES (9, N'0ed3a9ba-1ff2-4992-8883-7df0379f7afa', 28, 3, 26890000, N'2023-05-31T22:15:45.054057700', N'')
SET IDENTITY_INSERT [dbo].[order_details] OFF
GO
INSERT [dbo].[orders] ([id], [user_id], [name], [email], [phone], [address], [payment_mode], [status], [total_price]) VALUES (N'0ed3a9ba-1ff2-4992-8883-7df0379f7afa', 4, N'dat222', N'dat1@gmail.com', N'0815552939', N'dffffff', N'Cash', 0, 80670000)
INSERT [dbo].[orders] ([id], [user_id], [name], [email], [phone], [address], [payment_mode], [status], [total_price]) VALUES (N'1', 3, N'dat', N'ddat@gmail.com', N'0815552939', N'a', N'cast', 1, 2000)
INSERT [dbo].[orders] ([id], [user_id], [name], [email], [phone], [address], [payment_mode], [status], [total_price]) VALUES (N'6a2825ef-2ed9-4b92-9a12-eeb704da0791', 4, N'dat', N'dat1@gmail.com', N'0815552939', N'hhh', N'Cash', 1, 148140)
INSERT [dbo].[orders] ([id], [user_id], [name], [email], [phone], [address], [payment_mode], [status], [total_price]) VALUES (N'74429dc1-706f-4450-9579-feb0561d21ac', 4, N'dat222', N'dat1@gmail.com', N'0815552939', N'dffffff', N'Cash', 1, 19480000)
INSERT [dbo].[orders] ([id], [user_id], [name], [email], [phone], [address], [payment_mode], [status], [total_price]) VALUES (N'a3016e26-519f-40e7-9f31-81dccdad01e1', 4, N'dat222', N'dat1@gmail.com', N'0815552939', N'dffffff', N'Cash', 0, 26890000)
INSERT [dbo].[orders] ([id], [user_id], [name], [email], [phone], [address], [payment_mode], [status], [total_price]) VALUES (N'b4381fc5-62ac-4ae8-ae02-5c6ecaaf2827', 4, N'dat', N'dat1@gmail.com', N'0815552939', N'Ã?Â¡das', N'Cash', 1, 12468)
INSERT [dbo].[orders] ([id], [user_id], [name], [email], [phone], [address], [payment_mode], [status], [total_price]) VALUES (N'b9a5810d-f7ad-4233-92db-35581b60ee95', 4, N'dat', N'dat1@gmail.com', N'0815552939', N'Ã?Â¡das', N'Cash', 1, 738744)
GO
SET IDENTITY_INSERT [dbo].[products] ON 

INSERT [dbo].[products] ([id], [cate_id], [name], [description], [og_price], [selling_price], [image], [qty], [status], [trending]) VALUES (23, 20, N'Bluetooth Apple AirPods 2', N'Faster response and power savings thanks to the Apple H1 . chip
Luxurious, lightweight design makes it comfortable to wear for hours on end
Built-in 2 noise-canceling microphones for good sound quality when talking
Fast toll technology support, it only takes 15 minutes to have 3 hours of use', 3990000, 2790000, N'group_169_2.webp', 19, 1, 1)
INSERT [dbo].[products] ([id], [cate_id], [name], [description], [og_price], [selling_price], [image], [qty], [status], [trending]) VALUES (27, 31, N'Anker Charger Gen 2 PD 30W A2639', N'Compact size, convenient to carry anywhere
30W charging power ensures fast charging time
Smart chip control against electric leakage, short circuit
Type-C port compatible with most product lines', 500, 330, N'frame_1_10_.webp', 0, 1, 1)
INSERT [dbo].[products] ([id], [cate_id], [name], [description], [og_price], [selling_price], [image], [qty], [status], [trending]) VALUES (28, 21, N'iPhone 14 Pro Max 128GB', N'Dynamic Island screen - The disappearance of the notch screen replaced by a tablet design, 6.7-inch OLED, always-on display support
Powerful configuration of iPhone 14 Pro Max, great performance from A16 Bionic chipset
Mastering photography technology - 48MP rear camera, vivid TOF sensor
Built-in lithium-ion battery combined with innovative fast charging technology', 29990000, 26890000, N'1_252.webp', 16, 1, 1)
INSERT [dbo].[products] ([id], [cate_id], [name], [description], [og_price], [selling_price], [image], [qty], [status], [trending]) VALUES (29, 20, N'Samsung Galaxy Buds2', N'Immerse yourself in the world of music with an advanced 2-way speaker system and 360 . sound
The ultimate musical adventure thanks to active noise cancellation technology, which eliminates all background noise
Compact, dynamic design, free to exercise with tight-fitting earcups, limiting falls
Ultra-low latency, quick connection thanks to Bluetooth 5.2 for the full experience', 2990000, 1390000, N'frame_1_3_9.webp', 30, 1, 0)
INSERT [dbo].[products] ([id], [cate_id], [name], [description], [og_price], [selling_price], [image], [qty], [status], [trending]) VALUES (30, 21, N'iPhone 13 128GB ', N'Outstanding performance - Powerful Apple A15 Bionic chip, supports high-speed 5G network
Vibrant display space - 6.1" Super Retina XDR display with high brightness and sharpness
The ultimate cinematic experience - 12MP dual cameras with optical image stabilization
Power optimization - 20W fast charge, 50% full battery in about 30 minutes', 18990000, 16690000, N'14_1_9_2_9.webp', 19, 1, 0)
INSERT [dbo].[products] ([id], [cate_id], [name], [description], [og_price], [selling_price], [image], [qty], [status], [trending]) VALUES (31, 31, N'Anker PowerCore Select 20000mAh A1363', N'Large capacity battery up to 20,000 mAh supports multiple charging of electronic devices
Exclusive Power IQ and VoltageBoots technologies help optimize charging capacity to 3A
Anti-slip texture design and shell made of super durable polycarbonate rubber
Charge 2 devices at the same time with a maximum charging capacity of 15W with a USB-A output port', 1000000, 790000, N'anker-power-3.webp', 20, 1, 1)
INSERT [dbo].[products] ([id], [cate_id], [name], [description], [og_price], [selling_price], [image], [qty], [status], [trending]) VALUES (32, 31, N'Xiaomi Redmi 20000mAh', N'Large capacity 20,000 mAh
Fast Charge 18W
Charge two devices at the same time', 590, 580, N'group_153_1.webp', 20, 1, 1)
INSERT [dbo].[products] ([id], [cate_id], [name], [description], [og_price], [selling_price], [image], [qty], [status], [trending]) VALUES (33, 21, N'Samsung Galaxy S23 Ultra 256GB', N'Enjoy professional photography and video recording - Camera up to 200MP, improved night mode, intelligent image processor
Booming game - Snapdragon 8 Gen 2 octa-core chip increases processing speed, 120Hz screen, 5,000mAh battery
Boost your productivity with the built-in Super S Pen, easily highlight events from photos or videos
Durable, eco-friendly design - Nature-inspired colors, glass materials and recycled PET film coating', 31990000, 26030000, N'sm-s908_galaxys22ultra_front_burgundy_211119.webp', 10, 1, 0)
INSERT [dbo].[products] ([id], [cate_id], [name], [description], [og_price], [selling_price], [image], [qty], [status], [trending]) VALUES (34, 21, N'OPPO Find N2 Flip', N'Durable design that folds up to 400,000 times, making it easy to fold and carry
3.26-inch secondary screen for intuitive and detailed visibility
High-quality lens system with 50MP . Sony IMX890 sensor
Powerful performance with MediaTek 9000+ processor combined with 8GB RAM and 256GB ROM', 19990000, 19990000, N'n2_flip_-_combo_product_-_black.webp', 20, 1, 1)
INSERT [dbo].[products] ([id], [cate_id], [name], [description], [og_price], [selling_price], [image], [qty], [status], [trending]) VALUES (35, 35, N'Garmin Forerunner 265', N'Create a professional workout plan with the Garmin Coach virtual assistant
Extremely strong battery up to 13 days in smartwatch mode and 24 hours with continuous GPS enabled
Easily keep track of all the information on your screen with the vibrant Amoled background
Save music and enjoy right on the watch with 8GB internal memory', 10690000, 11690000, N'garminnnn.webp', 20, 1, 1)
INSERT [dbo].[products] ([id], [cate_id], [name], [description], [og_price], [selling_price], [image], [qty], [status], [trending]) VALUES (36, 20, N'Sony WH-1000XM5', N'Auto NC Optimizer technology automatically cancels noise based on the environment
Realistic, immersive listening experience thanks to 360 Reality Audio
Luxurious design, light weight with soft skin, close to the head
Power all day long with up to 40 hours of battery life', 7990000, 7590000, N'group_172_2.webp', 20, 1, 1)
SET IDENTITY_INSERT [dbo].[products] OFF
GO
SET IDENTITY_INSERT [dbo].[rate_prod] ON 

INSERT [dbo].[rate_prod] ([id], [user_id], [prod_id], [stars_rated], [updated_at]) VALUES (1, 4, 27, 3, NULL)
INSERT [dbo].[rate_prod] ([id], [user_id], [prod_id], [stars_rated], [updated_at]) VALUES (2, 4, 28, 5, NULL)
INSERT [dbo].[rate_prod] ([id], [user_id], [prod_id], [stars_rated], [updated_at]) VALUES (3, 4, 30, 5, NULL)
SET IDENTITY_INSERT [dbo].[rate_prod] OFF
GO
SET IDENTITY_INSERT [dbo].[users] ON 

INSERT [dbo].[users] ([id], [name], [email], [password], [phone], [address], [role], [state], [created_at], [updated_at]) VALUES (1, N'dat', N'dat@gmail.com', N'e10adc3949ba59abbe56e057f20f883e', N'123', N'ádas', N'client', N'actived', N'', NULL)
INSERT [dbo].[users] ([id], [name], [email], [password], [phone], [address], [role], [state], [created_at], [updated_at]) VALUES (2, N'dat111', N'dat@gmail.com', N'e10adc3949ba59abbe56e057f20f883e', N'0815552939', N'ádas', N'client', N'actived', N'', NULL)
INSERT [dbo].[users] ([id], [name], [email], [password], [phone], [address], [role], [state], [created_at], [updated_at]) VALUES (3, N'duongquocdat', N'datquoc.duong@dxc.com', N'e10adc3949ba59abbe56e057f20f883e', N'0815552939', N'42, Hiep Binh Chanh', N'admin', N'actived', N'', NULL)
INSERT [dbo].[users] ([id], [name], [email], [password], [phone], [address], [role], [state], [created_at], [updated_at]) VALUES (4, N'dat222', N'dat1@gmail.com', N'e10adc3949ba59abbe56e057f20f883e', N'0815552939', N'dffffff', N'client', N'actived', N'', NULL)
SET IDENTITY_INSERT [dbo].[users] OFF
GO
ALTER TABLE [dbo].[cart_items]  WITH CHECK ADD  CONSTRAINT [FK_carts_products] FOREIGN KEY([prod_id])
REFERENCES [dbo].[products] ([id])
GO
ALTER TABLE [dbo].[cart_items] CHECK CONSTRAINT [FK_carts_products]
GO
ALTER TABLE [dbo].[cart_items]  WITH CHECK ADD  CONSTRAINT [FK_carts_users] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[cart_items] CHECK CONSTRAINT [FK_carts_users]
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD  CONSTRAINT [FK_order_details_order] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([id])
GO
ALTER TABLE [dbo].[order_details] CHECK CONSTRAINT [FK_order_details_order]
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD  CONSTRAINT [FK_order_details_products] FOREIGN KEY([prod_id])
REFERENCES [dbo].[products] ([id])
GO
ALTER TABLE [dbo].[order_details] CHECK CONSTRAINT [FK_order_details_products]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_order_users] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_order_users]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FK_products_categories] FOREIGN KEY([cate_id])
REFERENCES [dbo].[categories] ([id])
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FK_products_categories]
GO
ALTER TABLE [dbo].[rate_prod]  WITH CHECK ADD  CONSTRAINT [FK_rate_prod_products] FOREIGN KEY([prod_id])
REFERENCES [dbo].[products] ([id])
GO
ALTER TABLE [dbo].[rate_prod] CHECK CONSTRAINT [FK_rate_prod_products]
GO
ALTER TABLE [dbo].[rate_prod]  WITH CHECK ADD  CONSTRAINT [FK_rate_prod_users] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[rate_prod] CHECK CONSTRAINT [FK_rate_prod_users]
GO
ALTER TABLE [dbo].[reviews]  WITH CHECK ADD  CONSTRAINT [FK_reviews_products] FOREIGN KEY([prod_id])
REFERENCES [dbo].[products] ([id])
GO
ALTER TABLE [dbo].[reviews] CHECK CONSTRAINT [FK_reviews_products]
GO
ALTER TABLE [dbo].[reviews]  WITH CHECK ADD  CONSTRAINT [FK_reviews_users] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[reviews] CHECK CONSTRAINT [FK_reviews_users]
GO
USE [master]
GO
ALTER DATABASE [phonedb] SET  READ_WRITE 
GO
