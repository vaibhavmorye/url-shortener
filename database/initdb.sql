create table `shortUrl` (
`short_url` VARCHAR(10),
`original_url` VARCHAR(1000),
`user_id` INT(8),
`created_at` DATETIME,
`last_visited` DATETIME,
`visit_count` INT(8),
`expired_at` DATETIME
)