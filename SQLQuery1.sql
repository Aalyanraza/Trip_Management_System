CREATE DATABASE TripManagementSystem;   
USE TripManagementSystem;

CREATE TABLE Users(
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(255) NOT NULL,
    Email NVARCHAR(255) NOT NULL,
    Password NVARCHAR(255) NOT NULL,
    FullName NVARCHAR(255) NOT NULL,
    Role NVARCHAR(40) CHECK(Role IN ('Admin', 'Traveler')) DEFAULT 'Traveler',
    DateCreation DATETIME DEFAULT GETDATE()
);


CREATE TABLE Itineraries(
    ItineraryID INT IDENTITY(1,1)PRIMARY KEY,
    UserID INT NOT NULL,
    TripTitle NVARCHAR(255) NOT NULL,
    TripStartDate DATE NOT NULL,
    TripEndDate DATE NOT NULL,
    Destination NVARCHAR(255) NOT NULL,
    DateCreation DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE Expenses(
    ExpenseID INT IDENTITY(1,1) PRIMARY KEY,
    ItineraryID INT NOT NULL,
    Category NVARCHAR(50) NOT NULL,
    Description NVARCHAR(MAX) NOT NULL,
    Amount DECIMAL(10,2) NOT NULL,
    DateCreation DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (ItineraryID) REFERENCES Itineraries(ItineraryID)
);

CREATE TABLE Feedback (
    FeedbackID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    Message NVARCHAR(MAX) NOT NULL,
    DateCreation DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE Collaborators (
    CollaboratorID INT IDENTITY(1,1) PRIMARY KEY,
    ItineraryID INT NOT NULL,
    UserID INT NOT NULL,
    Role NVARCHAR(40) CHECK(Role IN ('Viewer', 'Editor')) DEFAULT 'Viewer',
    FOREIGN KEY (ItineraryID) REFERENCES Itineraries(ItineraryID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE Notifications (
    NotificationID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    Message NVARCHAR(MAX) NOT NULL,
    ReadStatus BIT DEFAULT 0,
    DateCreation DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

Create Table TripChat(
    MessageID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    ItineraryID INT NOT NULL,
    Message NVARCHAR(MAX) NOT NULL,
    DateCreation DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ItineraryID) REFERENCES Itineraries(ItineraryID)
);

--Functions for database

Create Procedure UserLogin
    @Username NVARCHAR(255),
    @Password NVARCHAR(255),
    @Email NVARCHAR(255),
    @FullName NVARCHAR(255),
    @Role NVARCHAR(40)
AS
BEGIN
    INSERT INTO Users(Username, Password, Email, FullName, Role)
    VALUES(@Username, @Password, @Email, @FullName, @Role)
END;

Create PROCEDURE LogItinerary
    @UserID INT,
    @TripTitle NVARCHAR(255),
    @TripStartDate DATE,
    @TripEndDate DATE,
    @Destination NVARCHAR(255)
AS
BEGIN
    INSERT INTO Itineraries(UserID, TripTitle, TripStartDate, TripEndDate, Destination)
    VALUES(@UserID, @TripTitle, @TripStartDate, @TripEndDate, @Destination)
END;

Create PROCEDURE LogExpense
    @ItineraryID INT,
    @Category NVARCHAR(50),
    @Description NVARCHAR(MAX),
    @Amount DECIMAL(10,2)
AS
BEGIN
    INSERT INTO Expenses(ItineraryID, Category, Description, Amount)
    VALUES(@ItineraryID, @Category, @Description, @Amount)
END;

CREATE Procedure LogFeedback
    @UserID INT,
    @Message NVARCHAR(MAX)
AS
BEGIN
    INSERT INTO Feedback(UserID, Message)
    VALUES(@UserID, @Message)
END;

CREATE PROCEDURE LogCollaborator
    @ItineraryID INT,
    @UserID INT,
    @Role NVARCHAR(40)
AS
BEGIN
    INSERT INTO Collaborators(ItineraryID, UserID, Role)
    VALUES(@ItineraryID, @UserID, @Role)
END;