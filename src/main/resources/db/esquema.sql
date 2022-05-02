DROP TABLE IF EXISTS AUTORS CASCADE;
CREATE TABLE AUTORS
(
	ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY
	,NOM NVARCHAR(160) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS CATEGORIES CASCADE;
CREATE TABLE CATEGORIES
(
	ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY
	,NOM NVARCHAR(160) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS CUINES CASCADE;
CREATE TABLE CUINES
(
	ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY
	,NOM NVARCHAR(160) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS KEYWORDS CASCADE;
CREATE TABLE KEYWORDS
(
	ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY
	,NOM NVARCHAR(60) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS INGREDIENTS CASCADE;
CREATE TABLE INGREDIENTS
(
	ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY
	,INGREDIENT NVARCHAR(2500) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS RECEPTES CASCADE;
CREATE TABLE RECEPTES
(
	ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY
	,NOM NVARCHAR(150) NOT NULL UNIQUE
	,TS_PUBLICACIO TIMESTAMP NOT NULL
	,FK_ID_AUTOR SMALLINT NOT NULL REFERENCES AUTORS(ID)
	,DESCRIPCIO NVARCHAR(250) NOT NULL
	,IMATGE VARCHAR(250) NOT NULL
	,D_PREPARACIO INTERVAL DAY TO MINUTE NOT NULL
	,FK_ID_CATEGORIA SMALLINT NOT NULL REFERENCES CATEGORIES(ID)
	,FK_ID_CUINA SMALLINT DEFAULT NULL REFERENCES CUINES(ID)
	,INSTRUCCIONS NVARCHAR(2500) NOT NULL
	,NOM_SERVEIS TINYINT NOT NULL
	,D_TOTAL INTERVAL DAY TO MINUTE NOT NULL
	,D_COC INTERVAL DAY TO MINUTE DEFAULT NULL
);

DROP TABLE IF EXISTS ING_RECEPTES CASCADE;
CREATE TABLE ING_RECEPTES
(
	FK_ID_RECEPTA BIGINT NOT NULL REFERENCES RECEPTES(ID)
	,FK_ID_INGREDIENT BIGINT NOT NULL REFERENCES INGREDIENTS(ID)
	,QUANTITAT SMALLINT
	,MESURA CHAR(10)
	,PRIMARY KEY (FK_ID_RECEPTA, FK_ID_INGREDIENT)
);

DROP TABLE IF EXISTS KEYWORDS_RECEPTES CASCADE;
CREATE TABLE KEYWORDS_RECEPTES
(
	F_ID_KEYWORD BIGINT NOT NULL REFERENCES KEYWORDS(ID)
	,FK_ID_RECEPTA BIGINT NOT NULL REFERENCES RECEPTES(ID)
	,PRIMARY KEY (F_ID_KEYWORD,FK_ID_RECEPTA)
);

-- Per a cercar per keyword:
-- SELECT DISTINCT ID, NOM, KEYWORD  FROM VRECEPTESK WHERE KEYWORD='Recetas de Ensaladas';
DROP VIEW IF EXISTS VRECEPTESK CASCADE;
CREATE VIEW VRECEPTESK AS
SELECT R.*, K.NOM AS KEYWORD FROM RECEPTES R
LEFT JOIN KEYWORDS_RECEPTES KR
ON R.ID=KR.FK_ID_RECEPTA
LEFT JOIN KEYWORDS K
ON KR.F_ID_KEYWORD=K.ID
ORDER BY R.NOM, K.NOM;

-- Per a cercar receptes amb autor, categoria i cuina
DROP VIEW IF EXISTS  VRECEPTES CASCADE;
CREATE VIEW VRECEPTES AS
SELECT R.ID, R.NOM, TS_PUBLICACIO, FK_ID_AUTOR, DESCRIPCIO, IMATGE, D_PREPARACIO, FK_ID_CATEGORIA, FK_ID_CUINA, INSTRUCCIONS, NOM_SERVEIS, D_TOTAL, D_COC
, A.NOM AS AUTOR, C.NOM AS CATEGORIA, U.NOM AS CUINA
FROM RECEPTES R
LEFT JOIN AUTORS A ON A.ID=FK_ID_AUTOR
LEFT JOIN CATEGORIES C ON C.ID=FK_ID_CATEGORIA
LEFT JOIN CUINES U ON U.ID=FK_ID_CUINA;

-- Per a obtenir keywords de receptes
DROP VIEW IF EXISTS VRKEYWORDS CASCADE;
CREATE VIEW VRKEYWORDS AS
SELECT K.NOM, KR.FK_ID_RECEPTA FROM KEYWORDS_RECEPTES KR
LEFT JOIN KEYWORDS K ON K.ID=KR.F_ID_KEYWORD;

-- Per a obtenir ingredients de receptes
DROP VIEW IF EXISTS VRINGREDIENTS CASCADE;
CREATE VIEW VRINGREDIENTS AS
SELECT I.INGREDIENT, IR.QUANTITAT, IR.MESURA, IR.FK_ID_RECEPTA FROM ING_RECEPTES IR
LEFT JOIN INGREDIENTS I ON I.ID=IR.FK_ID_INGREDIENT;

-- Categories amb el pes (nombre de categories a les receptes)
DROP VIEW IF EXISTS VPES_CATEGORIES CASCADE;
CREATE VIEW VPES_CATEGORIES AS
SELECT COUNT(*) AS PES, C.ID, C.NOM
FROM
CATEGORIES C
RIGHT JOIN RECEPTES R
ON R.FK_ID_CATEGORIA=C.ID
GROUP BY C.NOM
ORDER BY PES DESC, NOM;
