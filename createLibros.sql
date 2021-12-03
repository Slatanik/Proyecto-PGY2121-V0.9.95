CREATE TABLE libreria.libro(
	idlibro INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(45) NOT NULL,
    autor VARCHAR(45) NOT NULL,
    publicacion DATE NOT NULL,
    precio INT NOT NULL,
    dosponible BIT(1) NOT NULL,
    PRIMARY KEY (idlibro)
);