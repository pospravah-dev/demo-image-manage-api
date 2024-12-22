create schema if not exists sshow;

create TABLE IF NOT EXISTS sshow.image_url (
    id BIGSERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    duration INT,
    addition_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS sshow.slideshow (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
--    slideshow_list varchar(40)
);

CREATE TABLE IF NOT EXISTS sshow.slideshow_image_url (
    slideshow_id BIGINT NOT NULL,
    image_id BIGINT NOT NULL,
    FOREIGN KEY (slideshow_id) REFERENCES sshow.slideshow(id),
    FOREIGN KEY (image_id) REFERENCES sshow.image_url(id)
);

create TABLE IF NOT EXISTS sshow.proof_of_play (
    id BIGSERIAL PRIMARY KEY,
    slideshow_id BIGINT NOT NULL,
    image_id BIGINT NOT NULL,
    play_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);