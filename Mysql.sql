-- Create the database
CREATE DATABASE TripManagementSystem;



-- Create the Users table
CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    FullName VARCHAR(255) NOT NULL,
    Role ENUM('Admin', 'Traveler') DEFAULT 'Traveler',
    DateCreation DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Create the Itineraries table
CREATE TABLE Itineraries (
    ItineraryID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    TripTitle VARCHAR(255) NOT NULL,
    TripStartDate DATE NOT NULL,
    TripEndDate DATE NOT NULL,
    Destination VARCHAR(255) NOT NULL,
    DateCreation DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Create the Expenses table
CREATE TABLE Expenses (
    ExpenseID INT AUTO_INCREMENT PRIMARY KEY,
    ItineraryID INT NOT NULL,
    Category VARCHAR(50) NOT NULL,
    Description TEXT NOT NULL,
    Amount DECIMAL(10,2) NOT NULL,
    DateCreation DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ItineraryID) REFERENCES Itineraries(ItineraryID)
);

-- Create the Feedback table
CREATE TABLE Feedback (
    FeedbackID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    Message TEXT NOT NULL,
    DateCreation DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Create the Collaborators table
CREATE TABLE Collaborators (
    CollaboratorID INT AUTO_INCREMENT PRIMARY KEY,
    ItineraryID INT NOT NULL,
    UserID INT NOT NULL,
    Role ENUM('Viewer', 'Editor') DEFAULT 'Viewer',
    FOREIGN KEY (ItineraryID) REFERENCES Itineraries(ItineraryID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Create the Notifications table
CREATE TABLE Notifications (
    NotificationID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    Message TEXT NOT NULL,
    ReadStatus BOOLEAN DEFAULT 0,
    DateCreation DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Create the TripChat table
CREATE TABLE TripChat (
    MessageID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    ItineraryID INT NOT NULL,
    Message TEXT NOT NULL,
    DateCreation DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ItineraryID) REFERENCES Itineraries(ItineraryID)
);

-- Create procedures

DELIMITER $$

CREATE PROCEDURE UserLogin (
    IN p_Username VARCHAR(255),
    IN p_Password VARCHAR(255),
    IN p_Email VARCHAR(255),
    IN p_FullName VARCHAR(255),
    IN p_Role ENUM('Admin', 'Traveler')
)
BEGIN
    INSERT INTO Users (Username, Password, Email, FullName, Role)
    VALUES (p_Username, p_Password, p_Email, p_FullName, p_Role);
END $$

CREATE PROCEDURE LogItinerary (
    IN p_UserID INT,
    IN p_TripTitle VARCHAR(255),
    IN p_TripStartDate DATE,
    IN p_TripEndDate DATE,
    IN p_Destination VARCHAR(255)
)
BEGIN
    INSERT INTO Itineraries (UserID, TripTitle, TripStartDate, TripEndDate, Destination)
    VALUES (p_UserID, p_TripTitle, p_TripStartDate, p_TripEndDate, p_Destination);
END $$

CREATE PROCEDURE LogExpense (
    IN p_ItineraryID INT,
    IN p_Category VARCHAR(50),
    IN p_Description TEXT,
    IN p_Amount DECIMAL(10,2)
)
BEGIN
    INSERT INTO Expenses (ItineraryID, Category, Description, Amount)
    VALUES (p_ItineraryID, p_Category, p_Description, p_Amount);
END $$

DELIMITER $$
CREATE PROCEDURE LogFeedback (
    IN p_UserID INT,
    IN p_Message TEXT
)
BEGIN
    INSERT INTO Feedback (UserID, Message)
    VALUES (p_UserID, p_Message);
END $$
DELIMITER ;
CREATE PROCEDURE LogCollaborator (
    IN p_ItineraryID INT,
    IN p_UserID INT,
    IN p_Role ENUM('Viewer', 'Editor')
)
BEGIN
    INSERT INTO Collaborators (ItineraryID, UserID, Role)
    VALUES (p_ItineraryID, p_UserID, p_Role);
END $$

DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetExpenses(IN p_ItineraryID INT)
BEGIN
    SELECT * FROM Expenses WHERE ItineraryID = p_ItineraryID;
END $$
DELIMITER ;
DELIMITER $$
CREATE PROCEDURE GetItinerary(IN p_ItineraryID INT)
BEGIN
    SELECT * FROM Itineraries WHERE ItineraryID = p_ItineraryID;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetUser(IN p_Username VARCHAR(255), IN p_Password VARCHAR(255))
BEGIN
    SELECT * FROM Users WHERE Username = p_Username AND Password = p_Password;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetAllUsers()
BEGIN
    SELECT UserID, Username, Email FROM Users;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE GetAllItineraries()
BEGIN
    SELECT ItineraryID, TripTitle, UserID FROM Itineraries;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE DeleteUser(IN p_UserID INT)
BEGIN
    DELETE FROM Expenses WHERE ItineraryID IN (SELECT ItineraryID FROM Itineraries WHERE UserID = p_UserID);
    DELETE FROM Itineraries WHERE UserID = p_UserID;
    DELETE FROM Feedback WHERE UserID = p_UserID;
    DELETE FROM Users WHERE UserID = p_UserID;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE DeleteItinerary(IN p_ItineraryID INT)
BEGIN
    DELETE FROM Expenses WHERE ItineraryID = p_ItineraryID;
    DELETE FROM Itineraries WHERE ItineraryID = p_ItineraryID;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE EditItinerary(
    IN p_ItineraryID INT,
    IN p_TripTitle VARCHAR(255),
    IN p_TripStartDate DATE,
    IN p_TripEndDate DATE,
    IN p_Destination VARCHAR(255)
)
BEGIN
    UPDATE Itineraries
    SET TripTitle = p_TripTitle, TripStartDate = p_TripStartDate, TripEndDate = p_TripEndDate, Destination = p_Destination
    WHERE ItineraryID = p_ItineraryID;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE AddFeedback(IN p_UserID INT, IN p_Message TEXT)
BEGIN
    INSERT INTO Feedback (UserID, Message) VALUES (p_UserID, p_Message);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE ViewFeedback(IN p_UserID INT)
BEGIN
    SELECT * FROM Feedback WHERE UserID = p_UserID;
END $$
DELIMITER ;

CALL UserLogin(
    'admin',         -- Username
    'admin',      -- Password
    'admin@admin.com', -- Email
    'admin',         -- Full Name
    'admin'          -- Role (can be 'Admin' or 'Traveler')
);

SELECT * FROM Users;
SELECT * FROM Users WHERE Username= 'john_doe' AND Password = 'password123';
SELECT FullName, Email, Preferences FROM Users WHERE UserID = 1;