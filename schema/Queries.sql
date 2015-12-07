Insert into HouseReview (UserID, HouseID, Review) values (?, ?, ?);

Insert into LandlordReview(UserID, LandlordID, Review) VALUES (?, ?, ?);

SELECT * FROM House JOIN Address ON House.AddressID = Address.AddressID WHERE Address.Street LIKE ?;

SELECT * FROM Landlord WHERE LordName LIKE ?;

SELECT * FROM HouseReview JOIN House ON HouseReview.HouseID = House.HouseID JOIN Address ON Address.AddressID = House.AddressID;

SELECT * FROM LandlordReview JOIN Landlord ON Landlord.LandlordID = LandlordReview.LandlordID;

UPDATE House SET LandlordID = (SELECT LandlordID FROM Landlord ORDER BY LandlordID DESC LIMIT 1) ORDER BY HouseID DESC LIMIT 1;

INSERT INTO Landlord (LordName) SELECT ? FROM Landlord WHERE NOT EXISTS ( SELECT LordName FROM Landlord WHERE LordName = ? ) LIMIT 1;

INSERT INTO House (AddressID) VALUES (LAST_INSERT_ID());

INSERT INTO Address (Street, City, State, Zip) VALUES (?, ?, ?, ?);
