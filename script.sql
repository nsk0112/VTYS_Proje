USE [master]
GO
/****** Object:  Database [Farm_db]    Script Date: 20.12.2022 23:45:42 ******/
CREATE DATABASE [Farm_db]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Farm_db', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Farm_db.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Farm_db_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Farm_db_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Farm_db] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Farm_db].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Farm_db] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Farm_db] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Farm_db] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Farm_db] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Farm_db] SET ARITHABORT OFF 
GO
ALTER DATABASE [Farm_db] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Farm_db] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Farm_db] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Farm_db] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Farm_db] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Farm_db] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Farm_db] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Farm_db] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Farm_db] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Farm_db] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Farm_db] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Farm_db] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Farm_db] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Farm_db] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Farm_db] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Farm_db] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Farm_db] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Farm_db] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Farm_db] SET  MULTI_USER 
GO
ALTER DATABASE [Farm_db] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Farm_db] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Farm_db] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Farm_db] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Farm_db] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Farm_db] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [Farm_db] SET QUERY_STORE = OFF
GO
USE [Farm_db]
GO
/****** Object:  User [test]    Script Date: 20.12.2022 23:45:42 ******/
CREATE USER [test] FOR LOGIN [test] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [proje_test]    Script Date: 20.12.2022 23:45:42 ******/
CREATE USER [proje_test] FOR LOGIN [proje_test] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [deneme2]    Script Date: 20.12.2022 23:45:42 ******/
CREATE USER [deneme2] FOR LOGIN [deneme2] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [proje_test]
GO
ALTER ROLE [db_owner] ADD MEMBER [deneme2]
GO
/****** Object:  Table [dbo].[Vehicle]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vehicle](
	[VehicleID] [int] IDENTITY(1,1) NOT NULL,
	[DateOfCheck] [date] NULL,
	[VehicleType] [varchar](20) NULL,
	[Purpose] [varchar](50) NULL,
	[NumberPlate] [varchar](6) NULL,
 CONSTRAINT [PK__Vehicle__476B54B2D98ABDCD] PRIMARY KEY CLUSTERED 
(
	[VehicleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[CheckDate]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[CheckDate]
AS
SELECT DATEDIFF(MONTH, DateOfCheck, CAST(GETDATE() AS Date)) AS [date diff]
FROM     dbo.Vehicle AS date_diff
GO
/****** Object:  Table [dbo].[Animal]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Animal](
	[AnimalID] [int] NOT NULL,
	[Species] [varchar](255) NULL,
	[AnimalType] [varchar](255) NULL,
	[Quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[AnimalID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryID] [int] NOT NULL,
	[CategoryName] [varchar](20) NULL,
	[CategoryDetails] [varchar](100) NULL,
 CONSTRAINT [PK__Category__19093A2BF8BD1875] PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[CustomerID] [int] NOT NULL,
	[CustomerName] [varchar](255) NULL,
	[CustomerAddress] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Farm]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Farm](
	[FarmID] [int] NOT NULL,
	[FarmerID] [int] NOT NULL,
	[ToolID] [int] NOT NULL,
	[VehicleID] [int] NOT NULL,
	[FieldID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[FarmID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Farmer]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Farmer](
	[FarmerID] [int] NOT NULL,
	[FarmerFirstName] [varchar](20) NULL,
	[FarmerLastName] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[FarmerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Field]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Field](
	[FieldID] [int] NOT NULL,
	[ProductID] [int] NULL,
	[Area] [float] NULL,
	[FieldValue] [float] NULL,
	[TotalEarnings] [float] NULL,
	[FarmerID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[FieldID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Harvest]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Harvest](
	[HarvestID] [int] NOT NULL,
	[FieldID] [int] NULL,
	[VehicleID] [int] NULL,
	[ProductID] [int] NULL,
	[HarvestDate] [date] NULL,
	[Quantitiy] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[HarvestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HarvestDetails]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HarvestDetails](
	[HarvestID] [int] NOT NULL,
	[FarmerID] [int] NULL,
	[ToolID] [int] NULL,
	[HarvestAmount] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[HarvestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ProductID] [int] NOT NULL,
	[CategoryID] [int] NULL,
	[ProductName] [varchar](20) NULL,
	[UnitPrice] [money] NULL,
	[Quantity] [int] NULL,
 CONSTRAINT [PK__Product__B40CC6ED5E1BCFAF] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Sales]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sales](
	[SaleID] [int] NOT NULL,
	[CustomerID] [int] NULL,
	[SaleDate] [date] NULL,
	[Paid] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[SaleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SalesDetails]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SalesDetails](
	[SalesID] [int] NOT NULL,
	[ProductID] [int] NULL,
	[Price] [money] NULL,
	[Quantity] [int] NULL,
	[SalesDetailsID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[SalesID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tools]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tools](
	[ToolID] [int] IDENTITY(1,1) NOT NULL,
	[ToolName] [varchar](20) NOT NULL,
	[Quantity] [int] NULL,
	[Explanation] [varchar](255) NULL,
 CONSTRAINT [PK__Tools__CC0CEBB104D9CD58] PRIMARY KEY CLUSTERED 
(
	[ToolID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 20.12.2022 23:45:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserName] [varchar](6) NULL,
	[UserPassword] [varchar](6) NULL,
	[FarmerID] [int] NOT NULL
) ON [PRIMARY]
GO
INSERT [dbo].[Animal] ([AnimalID], [Species], [AnimalType], [Quantity]) VALUES (1, N'Goat', N'Mammals', 22)
INSERT [dbo].[Animal] ([AnimalID], [Species], [AnimalType], [Quantity]) VALUES (2, N'Dog', N'Mammals', 4)
INSERT [dbo].[Animal] ([AnimalID], [Species], [AnimalType], [Quantity]) VALUES (3, N'Cow', N'Mammals', 12)
INSERT [dbo].[Animal] ([AnimalID], [Species], [AnimalType], [Quantity]) VALUES (4, N'Horse', N'Mammals', 7)
INSERT [dbo].[Animal] ([AnimalID], [Species], [AnimalType], [Quantity]) VALUES (5, N'Sheep', N'Mammals', 89)
INSERT [dbo].[Animal] ([AnimalID], [Species], [AnimalType], [Quantity]) VALUES (6, N'Chicken', N'Birds', 144)
INSERT [dbo].[Animal] ([AnimalID], [Species], [AnimalType], [Quantity]) VALUES (7, N'Rabbit', N'Mammals', 3)
INSERT [dbo].[Animal] ([AnimalID], [Species], [AnimalType], [Quantity]) VALUES (8, N'Donkey', N'Mammels', 2)
INSERT [dbo].[Animal] ([AnimalID], [Species], [AnimalType], [Quantity]) VALUES (9, N'Ducks', N'Birds', 4)
INSERT [dbo].[Animal] ([AnimalID], [Species], [AnimalType], [Quantity]) VALUES (10, N'Turkeys', N'Birds', 14)
GO
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [CategoryDetails]) VALUES (100, N'Herbal', N'Plant based and herbal products')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [CategoryDetails]) VALUES (200, N'Animal', N'Animal products')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [CategoryDetails]) VALUES (300, N'Hypoallergenic', N'Allergen free products')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [CategoryDetails]) VALUES (400, N'Vegetarian', N'Products that don''t contain animal flesh')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [CategoryDetails]) VALUES (500, N'Vegan', N'Products that don''t contain ingredients derived from animals')
GO
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [CustomerAddress]) VALUES (99001, N'Rafaela Gupta', N'351 53rd Dr.
San Carlos, CA 94070')
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [CustomerAddress]) VALUES (99002, N'Blanche Barzetti', N'359 Grove Street
Onalaska, WI 54650')
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [CustomerAddress]) VALUES (99003, N'Seffora Madden', N'8113 South High Ridge St.
Reynoldsburg, OH 43068')
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [CustomerAddress]) VALUES (99004, N'Galen Rolland', N'72 Newbridge Street
Sheboygan, WI 53081')
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [CustomerAddress]) VALUES (99005, N'Chernobog Greene', N'975 W. Fawn Court
Jamaica Plain, MA 02130')
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [CustomerAddress]) VALUES (99006, N'Rhett Darrell', N'35 NW. Van Dyke Court
Rome, NY 13440')
GO
INSERT [dbo].[Farm] ([FarmID], [FarmerID], [ToolID], [VehicleID], [FieldID]) VALUES (17001, 10001, 7, 10, 2)
INSERT [dbo].[Farm] ([FarmID], [FarmerID], [ToolID], [VehicleID], [FieldID]) VALUES (17002, 10002, 15, 11, 1)
INSERT [dbo].[Farm] ([FarmID], [FarmerID], [ToolID], [VehicleID], [FieldID]) VALUES (17003, 10003, 9, 11, 2)
INSERT [dbo].[Farm] ([FarmID], [FarmerID], [ToolID], [VehicleID], [FieldID]) VALUES (17004, 20100, 15, 11, 2)
INSERT [dbo].[Farm] ([FarmID], [FarmerID], [ToolID], [VehicleID], [FieldID]) VALUES (17005, 20101, 13, 11, 3)
INSERT [dbo].[Farm] ([FarmID], [FarmerID], [ToolID], [VehicleID], [FieldID]) VALUES (17006, 20102, 15, 11, 3)
INSERT [dbo].[Farm] ([FarmID], [FarmerID], [ToolID], [VehicleID], [FieldID]) VALUES (17007, 20103, 2, 11, 4)
INSERT [dbo].[Farm] ([FarmID], [FarmerID], [ToolID], [VehicleID], [FieldID]) VALUES (17008, 20104, 8, 11, 3)
GO
INSERT [dbo].[Farmer] ([FarmerID], [FarmerFirstName], [FarmerLastName]) VALUES (10001, N'Russell', N'Erickson')
INSERT [dbo].[Farmer] ([FarmerID], [FarmerFirstName], [FarmerLastName]) VALUES (10002, N'Sophia', N'Clark')
INSERT [dbo].[Farmer] ([FarmerID], [FarmerFirstName], [FarmerLastName]) VALUES (10003, N'Austin', N'Moss')
INSERT [dbo].[Farmer] ([FarmerID], [FarmerFirstName], [FarmerLastName]) VALUES (20100, N'Godfrey', N'Norris')
INSERT [dbo].[Farmer] ([FarmerID], [FarmerFirstName], [FarmerLastName]) VALUES (20101, N'Anthony', N'Patton')
INSERT [dbo].[Farmer] ([FarmerID], [FarmerFirstName], [FarmerLastName]) VALUES (20102, N'Jim', N'Hill')
INSERT [dbo].[Farmer] ([FarmerID], [FarmerFirstName], [FarmerLastName]) VALUES (20103, N'Cayden', N'Davis')
INSERT [dbo].[Farmer] ([FarmerID], [FarmerFirstName], [FarmerLastName]) VALUES (20104, N'Oscar', N'Turner')
GO
INSERT [dbo].[Field] ([FieldID], [ProductID], [Area], [FieldValue], [TotalEarnings], [FarmerID]) VALUES (1, 105, 2, 23000, 2560, 10002)
INSERT [dbo].[Field] ([FieldID], [ProductID], [Area], [FieldValue], [TotalEarnings], [FarmerID]) VALUES (2, 103, 25, 300000, 120000, 10003)
INSERT [dbo].[Field] ([FieldID], [ProductID], [Area], [FieldValue], [TotalEarnings], [FarmerID]) VALUES (3, 102, 7, 100000, 44000, 20104)
INSERT [dbo].[Field] ([FieldID], [ProductID], [Area], [FieldValue], [TotalEarnings], [FarmerID]) VALUES (4, 103, 4, 50000, 15000, 20103)
GO
INSERT [dbo].[Product] ([ProductID], [CategoryID], [ProductName], [UnitPrice], [Quantity]) VALUES (101, 100, N'Wheat', 8.7286, 12000)
INSERT [dbo].[Product] ([ProductID], [CategoryID], [ProductName], [UnitPrice], [Quantity]) VALUES (102, 100, N'Corn', 6.3400, 7500)
INSERT [dbo].[Product] ([ProductID], [CategoryID], [ProductName], [UnitPrice], [Quantity]) VALUES (103, 100, N'Cabbage', 11.5465, 4400)
INSERT [dbo].[Product] ([ProductID], [CategoryID], [ProductName], [UnitPrice], [Quantity]) VALUES (104, 100, N'Ginger Gold Apple', 6.6455, 5000)
INSERT [dbo].[Product] ([ProductID], [CategoryID], [ProductName], [UnitPrice], [Quantity]) VALUES (105, 100, N'Granny Smith Apple', 7.5665, 4000)
INSERT [dbo].[Product] ([ProductID], [CategoryID], [ProductName], [UnitPrice], [Quantity]) VALUES (106, 100, N'Walnut', 17.8561, 3200)
INSERT [dbo].[Product] ([ProductID], [CategoryID], [ProductName], [UnitPrice], [Quantity]) VALUES (201, 200, N'Cow Milk', 20.5000, 1240)
INSERT [dbo].[Product] ([ProductID], [CategoryID], [ProductName], [UnitPrice], [Quantity]) VALUES (202, 200, N'Goat Milk', 32.4487, 450)
INSERT [dbo].[Product] ([ProductID], [CategoryID], [ProductName], [UnitPrice], [Quantity]) VALUES (203, 200, N'Chicken Egg', 0.1235, 600)
INSERT [dbo].[Product] ([ProductID], [CategoryID], [ProductName], [UnitPrice], [Quantity]) VALUES (204, 200, N'Honey', 2.9846, 1200)
GO
INSERT [dbo].[Sales] ([SaleID], [CustomerID], [SaleDate], [Paid]) VALUES (19001, 99001, CAST(N'2019-10-25' AS Date), 1)
INSERT [dbo].[Sales] ([SaleID], [CustomerID], [SaleDate], [Paid]) VALUES (19002, 99005, CAST(N'2019-10-16' AS Date), 1)
INSERT [dbo].[Sales] ([SaleID], [CustomerID], [SaleDate], [Paid]) VALUES (19003, 99004, CAST(N'2019-09-22' AS Date), 1)
INSERT [dbo].[Sales] ([SaleID], [CustomerID], [SaleDate], [Paid]) VALUES (19004, 99006, CAST(N'2019-10-18' AS Date), 1)
INSERT [dbo].[Sales] ([SaleID], [CustomerID], [SaleDate], [Paid]) VALUES (19005, 99002, CAST(N'2019-09-12' AS Date), 1)
INSERT [dbo].[Sales] ([SaleID], [CustomerID], [SaleDate], [Paid]) VALUES (19006, 99003, CAST(N'2019-10-25' AS Date), 1)
INSERT [dbo].[Sales] ([SaleID], [CustomerID], [SaleDate], [Paid]) VALUES (19007, 99001, CAST(N'2019-09-23' AS Date), 1)
INSERT [dbo].[Sales] ([SaleID], [CustomerID], [SaleDate], [Paid]) VALUES (19008, 99001, CAST(N'2019-10-11' AS Date), 1)
INSERT [dbo].[Sales] ([SaleID], [CustomerID], [SaleDate], [Paid]) VALUES (19009, 99003, CAST(N'2019-10-30' AS Date), 1)
INSERT [dbo].[Sales] ([SaleID], [CustomerID], [SaleDate], [Paid]) VALUES (19010, 99005, CAST(N'2019-09-04' AS Date), 1)
INSERT [dbo].[Sales] ([SaleID], [CustomerID], [SaleDate], [Paid]) VALUES (19011, 99004, CAST(N'2019-10-02' AS Date), 1)
GO
SET IDENTITY_INSERT [dbo].[Tools] ON 

INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (1, N'Plow', 4, N'Tractor extension for loosening and turning soil.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (2, N'Harrow', 3, N'Tractor extension used to pulverize soil, break up crop residues, uproot weeds, and cover seed.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (3, N'Fertilizer Spreader', 3, N'Tractor extension to spread fertilizer across a field.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (4, N'Seeder', 5, N'Tractor extension to spread seeds across large plots of land.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (5, N'Baler', 2, N'Tractor extension to compress a cut and raked crop into compact bales that are easy to handle.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (6, N'Wagon', 6, N'Farm wagons and trailers are a necessity for any operation.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (7, N'Sprayer', 3, N'Can be used to spray pesticides, fertilizers and other substances across large areas.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (8, N'Shovel', 11, N'Shovel tool helps with digging and transplanting soil, making shallow trenches, and in removing dirt or debris.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (9, N'Pruning Shear', 10, N'Heavy-duty scissors specifically designed to cut plant stems and hard shrub branches.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (10, N'Pickaxe', 11, N'A hand tool that is usually T-shaped and used to pry.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (11, N'Axe', 7, N'Used mainly for shaping, splitting and cutting of wood.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (12, N'Rake', 10, N'A rake is a type of gardening or landscaping tool with a handle that ends in a head.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (13, N'Trowel', 21, N'A small garden tool which you use for digging small holes or removing weeds.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (14, N'Knife', 17, N'Tool for weeding, transplanting, cutting sod, and dividing plants.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (15, N'Wheelbarrow', 15, N'Used for a variety of things; such as moving things, hauling bricks  or mixing concrete or fertilizers.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (16, N'Sickle', 7, N'Used to harvest bundles of plants (like grasses or grains) by cutting them at the base.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (17, N'Scythe', 7, N'The scythe is used to cut grasses and grains.')
INSERT [dbo].[Tools] ([ToolID], [ToolName], [Quantity], [Explanation]) VALUES (18, N'Cultipacker', 2, N'Tractor extension that crushes dirt clods and presses down small stones, forming a smooth, firm seedbed, ensuring shallow seed placement and good seed-to-soil contact.')
SET IDENTITY_INSERT [dbo].[Tools] OFF
GO
INSERT [dbo].[Users] ([UserName], [UserPassword], [FarmerID]) VALUES (N'ericks', N'asd123', 10001)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FarmerID]) VALUES (N'sophia', N'zxc123', 10002)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FarmerID]) VALUES (N'aumoss', N'qwe123', 10003)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FarmerID]) VALUES (N'norris', N'rty123', 20100)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FarmerID]) VALUES (N'patton', N'pat123', 20101)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FarmerID]) VALUES (N'jimhil', N'jim123', 20103)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FarmerID]) VALUES (N'caydav', N'cay123', 20103)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FarmerID]) VALUES (N'turner', N'turner', 20104)
GO
SET IDENTITY_INSERT [dbo].[Vehicle] ON 

INSERT [dbo].[Vehicle] ([VehicleID], [DateOfCheck], [VehicleType], [Purpose], [NumberPlate]) VALUES (1, CAST(N'2019-03-11' AS Date), N'Tractor', N'Farming', N'05AS24')
INSERT [dbo].[Vehicle] ([VehicleID], [DateOfCheck], [VehicleType], [Purpose], [NumberPlate]) VALUES (2, CAST(N'2019-11-05' AS Date), N'Tractor', N'Farming', N'05DC95')
INSERT [dbo].[Vehicle] ([VehicleID], [DateOfCheck], [VehicleType], [Purpose], [NumberPlate]) VALUES (3, CAST(N'2019-11-05' AS Date), N'Tractor', N'Farming', N'07DT66')
INSERT [dbo].[Vehicle] ([VehicleID], [DateOfCheck], [VehicleType], [Purpose], [NumberPlate]) VALUES (4, CAST(N'2021-06-15' AS Date), N'Truck', N'Transport', N'05TF84')
INSERT [dbo].[Vehicle] ([VehicleID], [DateOfCheck], [VehicleType], [Purpose], [NumberPlate]) VALUES (5, CAST(N'2019-07-16' AS Date), N'Truck', N'Transport', N'07YZ45')
INSERT [dbo].[Vehicle] ([VehicleID], [DateOfCheck], [VehicleType], [Purpose], [NumberPlate]) VALUES (6, CAST(N'2020-10-23' AS Date), N'Car', N'Transport', N'05HG10')
INSERT [dbo].[Vehicle] ([VehicleID], [DateOfCheck], [VehicleType], [Purpose], [NumberPlate]) VALUES (7, CAST(N'2021-02-04' AS Date), N'Car', N'Transport', N'05UB84')
INSERT [dbo].[Vehicle] ([VehicleID], [DateOfCheck], [VehicleType], [Purpose], [NumberPlate]) VALUES (8, CAST(N'2020-01-21' AS Date), N'Car', N'Transport', N'05OL54')
INSERT [dbo].[Vehicle] ([VehicleID], [DateOfCheck], [VehicleType], [Purpose], [NumberPlate]) VALUES (9, CAST(N'2020-01-21' AS Date), N'Car', N'Transport', N'05FF51')
INSERT [dbo].[Vehicle] ([VehicleID], [DateOfCheck], [VehicleType], [Purpose], [NumberPlate]) VALUES (10, CAST(N'2022-02-02' AS Date), N'Harvester', N'Harvest', N'07TG95')
INSERT [dbo].[Vehicle] ([VehicleID], [DateOfCheck], [VehicleType], [Purpose], [NumberPlate]) VALUES (17, CAST(N'2020-05-13' AS Date), N'Harvester', N'Harvest', N'05HC65')
SET IDENTITY_INSERT [dbo].[Vehicle] OFF
GO
ALTER TABLE [dbo].[Field]  WITH CHECK ADD FOREIGN KEY([FarmerID])
REFERENCES [dbo].[Farmer] ([FarmerID])
GO
ALTER TABLE [dbo].[Field]  WITH CHECK ADD  CONSTRAINT [FK__Field__ProductID__2D27B809] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[Field] CHECK CONSTRAINT [FK__Field__ProductID__2D27B809]
GO
ALTER TABLE [dbo].[Harvest]  WITH CHECK ADD  CONSTRAINT [FK__Harvest__Vehicle__30F848ED] FOREIGN KEY([VehicleID])
REFERENCES [dbo].[Vehicle] ([VehicleID])
GO
ALTER TABLE [dbo].[Harvest] CHECK CONSTRAINT [FK__Harvest__Vehicle__30F848ED]
GO
ALTER TABLE [dbo].[HarvestDetails]  WITH CHECK ADD FOREIGN KEY([FarmerID])
REFERENCES [dbo].[Farmer] ([FarmerID])
GO
ALTER TABLE [dbo].[HarvestDetails]  WITH CHECK ADD FOREIGN KEY([HarvestID])
REFERENCES [dbo].[Harvest] ([HarvestID])
GO
ALTER TABLE [dbo].[HarvestDetails]  WITH CHECK ADD  CONSTRAINT [FK__HarvestDe__ToolI__37A5467C] FOREIGN KEY([ToolID])
REFERENCES [dbo].[Tools] ([ToolID])
GO
ALTER TABLE [dbo].[HarvestDetails] CHECK CONSTRAINT [FK__HarvestDe__ToolI__37A5467C]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK__Product__Categor__2A4B4B5E] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([CategoryID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK__Product__Categor__2A4B4B5E]
GO
ALTER TABLE [dbo].[Sales]  WITH CHECK ADD FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customer] ([CustomerID])
GO
ALTER TABLE [dbo].[SalesDetails]  WITH CHECK ADD  CONSTRAINT [FK__SalesDeta__Produ__403A8C7D] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[SalesDetails] CHECK CONSTRAINT [FK__SalesDeta__Produ__403A8C7D]
GO
ALTER TABLE [dbo].[SalesDetails]  WITH CHECK ADD FOREIGN KEY([SalesID])
REFERENCES [dbo].[Sales] ([SaleID])
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK__Users__FarmerID__48CFD27E] FOREIGN KEY([FarmerID])
REFERENCES [dbo].[Farmer] ([FarmerID])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK__Users__FarmerID__48CFD27E]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[41] 4[20] 2[6] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "date_diff"
            Begin Extent = 
               Top = 7
               Left = 48
               Bottom = 170
               Right = 242
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1200
         Width = 1200
         Width = 1200
         Width = 1200
         Width = 1200
         Width = 1200
         Width = 1200
         Width = 1200
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'CheckDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'CheckDate'
GO
USE [master]
GO
ALTER DATABASE [Farm_db] SET  READ_WRITE 
GO
