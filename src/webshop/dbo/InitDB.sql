CREATE TABLE Users (
    ID       INTEGER PRIMARY KEY AUTOINCREMENT,
    Name     TEXT    NOT NULL
                     UNIQUE,
    Role     TEXT    NOT NULL,
    Password TEXT,
    Status   STRING
);


CREATE TABLE Products (
    Id       INTEGER PRIMARY KEY AUTOINCREMENT,
    Name     TEXT,
    Category TEXT,
    Price    INTEGER,
    Status   STRING  NOT NULL
                     DEFAULT ENABLED
);



CREATE TABLE Sales (
    ID        INTEGER  PRIMARY KEY AUTOINCREMENT,
    DateTime  DATETIME NOT NULL,
    UserID    INTEGER  REFERENCES Users (ID),
    ProductID INTEGER  REFERENCES Products (Id),
    SaleID    INTEGER  NOT NULL,
    Price     REAL     NOT NULL,
    Quantity  INT      NOT NULL,
    SaleSum   REAL     NOT NULL,
    FOREIGN KEY (
        UserID
    )
    REFERENCES [Users.ID],
    FOREIGN KEY (
        ProductID
    )
    REFERENCES [Products.ID]
);

CREATE TABLE Basket (
    Id        INTEGER PRIMARY KEY AUTOINCREMENT,
    UserId    INTEGER REFERENCES Users (ID),
    ProductId INTEGER REFERENCES Sales (ID),
    Quantity  INTEGER DEFAULT (0) 
                      NOT NULL
);
