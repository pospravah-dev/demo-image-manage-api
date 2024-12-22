-- Insert data into image_url
INSERT INTO sshow.image_url (url, duration, addition_date) VALUES
('https://example.com/image1.jpg', 10, '2023-12-18 10:00:00'),
('https://example.com/image2.jpg', 20, '2023-12-16 11:00:00'),
('https://example.com/image3.jpg', 15, '2023-12-17 12:00:00'),
('https://example.com/image4.jpg', 25, '2023-12-19 13:00:00'),
('https://example.com/image5.jpg', 30, '2023-12-20 14:00:00');

-- Insert data into slideshow
INSERT INTO sshow.slideshow (title, description, created_at) VALUES
('Holiday Slideshow', 'A collection of holiday photos.', '2023-12-16 10:00:00'),
('Nature Slideshow', 'Beautiful pictures of nature.', '2023-12-17 11:00:00');


-- Insert data into slideshow_image_url to link slideshows and images
INSERT INTO sshow.slideshow_image_url (slideshow_id, image_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 2),
(2, 4),
(2, 5);