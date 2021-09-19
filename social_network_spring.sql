-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Сен 19 2021 г., 08:05
-- Версия сервера: 10.4.18-MariaDB
-- Версия PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `social_network_spring`
--

-- --------------------------------------------------------

--
-- Структура таблицы `comment`
--

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `comment`
--

INSERT INTO `comment` (`id`, `comment`, `date`, `post_id`, `user_id`) VALUES
(1, 'Заходи!', '2021-09-14 06:29:28', 19, 2),
(3, 'q', '2021-09-14 08:18:00', 23, 2),
(4, 'Заходи!', '2021-09-14 09:19:31', 19, 2),
(5, 'УРА Я НЕ ДУРАК', '2021-09-14 09:19:38', 17, 2),
(6, 'УРА Я НЕ ДУРАК', '2021-09-14 09:19:44', 4, 2),
(7, 'Хорошо', '2021-09-14 09:24:23', 23, 2),
(8, 'Хорошо', '2021-09-14 09:40:11', 22, 2),
(9, 'ПРИВЕТ!', '2021-09-14 09:56:40', 9, 2),
(10, 'hi', '2021-09-14 10:02:31', 20, 2),
(14, 'qwerqwer', '2021-09-15 04:56:52', 9, 2),
(17, 'qwe', '2021-09-15 07:27:38', 27, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `followers`
--

CREATE TABLE `followers` (
  `id` bigint(20) NOT NULL,
  `follower_id` bigint(20) DEFAULT NULL,
  `subscriber_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `followers`
--

INSERT INTO `followers` (`id`, `follower_id`, `subscriber_id`) VALUES
(15, 3, 2),
(16, 2, 3),
(17, 1, 2),
(18, 2, 4),
(19, 2, 1),
(20, 3, 4),
(21, 4, 2),
(22, 2, 6),
(23, 3, 1),
(24, 6, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `like_post`
--

CREATE TABLE `like_post` (
  `id` bigint(20) NOT NULL,
  `do_like` bit(1) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `like_post`
--

INSERT INTO `like_post` (`id`, `do_like`, `user_id`, `post_id`) VALUES
(10, NULL, 3, 9),
(15, NULL, 3, 4),
(20, NULL, 3, 17),
(21, NULL, 3, 18),
(29, NULL, 2, 19),
(38, NULL, 2, 20),
(40, NULL, 2, 18),
(41, NULL, 2, 21),
(43, NULL, 2, 22),
(44, NULL, 2, 23),
(49, NULL, 2, 9),
(53, NULL, 2, 26),
(54, NULL, 3, 27);

-- --------------------------------------------------------

--
-- Структура таблицы `message`
--

CREATE TABLE `message` (
  `id` bigint(20) NOT NULL,
  `user1_id` bigint(20) DEFAULT NULL,
  `user2_id` bigint(20) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `message`
--

INSERT INTO `message` (`id`, `user1_id`, `user2_id`, `date`, `message`) VALUES
(1, 2, 3, '2021-09-17 07:37:06', 'hi'),
(2, 3, 2, '2021-09-17 07:37:06', 'hi'),
(3, 2, 3, '2021-09-17 07:37:06', 'как ты?'),
(4, 3, 2, '2021-09-17 07:37:06', 'хорошо ты как?'),
(5, 2, 3, '2021-09-17 07:37:06', 'что делаешь?'),
(6, 3, 2, '2021-09-18 05:52:36', 'ничего'),
(7, 3, 2, '2021-09-18 05:52:43', 'а ты?'),
(8, 2, 3, '2021-09-18 05:55:41', 'тоже'),
(9, 2, 3, '2021-09-18 05:56:47', 'этот сайт делаю)'),
(10, 2, 4, '2021-09-18 05:59:36', 'здарова)'),
(11, 2, 6, '2021-09-18 07:34:51', 'ууу, здарова!'),
(12, 3, 4, '2021-09-18 07:38:44', 'О, здарова)'),
(13, 1, 5, '2021-09-18 11:37:08', 'Будешь моим другом?'),
(14, 3, 6, '2021-09-19 05:33:18', '1234'),
(15, 6, 3, '2021-09-19 06:00:38', '4321');

-- --------------------------------------------------------

--
-- Структура таблицы `post`
--

CREATE TABLE `post` (
  `id` bigint(20) NOT NULL,
  `count_like` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `for_users` bigint(20) DEFAULT NULL,
  `post_text` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `like_id` bigint(20) DEFAULT NULL,
  `like_post_id` bigint(20) DEFAULT NULL,
  `comment_id` bigint(20) DEFAULT NULL,
  `count_comment` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `post`
--

INSERT INTO `post` (`id`, `count_like`, `date`, `for_users`, `post_text`, `user_id`, `like_id`, `like_post_id`, `comment_id`, `count_comment`) VALUES
(4, 1, '2021-09-09 06:01:28', 2, 'Я ТУТ!!', 2, NULL, NULL, NULL, 1),
(9, 2, '2021-09-10 05:33:39', 1, 'ПРИВЕТ!', 3, NULL, NULL, NULL, 2),
(17, 1, '2021-09-12 04:35:43', 2, 'Я ТУТ!!', 3, NULL, NULL, NULL, 1),
(18, 2, '2021-09-12 04:35:59', 3, 'Я ТУТ!!', 3, NULL, NULL, NULL, 0),
(19, 1, '2021-09-12 05:15:10', 2, 'Что нового?', 2, NULL, NULL, 1, 2),
(20, 1, '2021-09-13 07:31:49', 1, 'hi', 2, NULL, NULL, NULL, 1),
(21, 1, '2021-09-13 07:46:32', 3, 'qwer', 2, NULL, NULL, NULL, 0),
(22, 1, '2021-09-14 06:25:14', 4, 'Я с ВАМИ', 4, NULL, NULL, NULL, 1),
(23, 1, '2021-09-14 08:17:45', 3, 'q', 2, NULL, NULL, 3, 2),
(26, 1, '2021-09-15 06:05:58', 2, 'qweqwe', 2, NULL, NULL, NULL, 0),
(27, 1, '2021-09-15 07:27:35', 3, 'qwe', 3, NULL, NULL, NULL, 1),
(28, 0, '2021-09-18 11:24:00', 4, 'Что нового?', 3, NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Структура таблицы `post_like_post`
--

CREATE TABLE `post_like_post` (
  `post_id` bigint(20) NOT NULL,
  `like_post_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `roles`
--

INSERT INTO `roles` (`id`, `role`) VALUES
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `about` varchar(255) DEFAULT NULL,
  `birthdate` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `followers_count` int(11) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` int(11) NOT NULL,
  `post_count` int(11) NOT NULL,
  `subscribers_count` int(11) NOT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `university` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `about`, `birthdate`, `city`, `followers_count`, `gender`, `image_path`, `name`, `phone`, `post_count`, `subscribers_count`, `surname`, `university`) VALUES
(1, 'm@mail.ru', '$2a$10$8E2Mg18.a7HyfG9ozYRLdOFN.06djzDzsZruvzdpzHdnzhJETDm/K', '', NULL, 'Nur-sultan', 1, NULL, '30fe8afc-7539-41ad-85a3-d4568bf61bce.main_blue.png', 'Not NULL', 0, 0, 3, 'SMITH', ''),
(2, 'qwer@mail.ru', '$2a$10$EtxzyxWvafTwD.ks8wEmFe0ybNKLrZ9mhzygnvPMvfmxFRA0inDSC', 'Я КРУТОЙ!', '12 Янв 1911', 'ALmaty', 4, 'Женский', '8bd7d7e5-12a6-479e-92db-da738be1425b.caveman-spongebob-spongegar.png', 'Мike', 88888888, 0, 3, 'Мike', 'AK Prestij'),
(3, 'd@mail.ru', '$2a$10$VaGFrl/dl1Q0N52el//cGOabRcxVDVd5V5UcVpGfKtcN5ho0yjBji', 'Не указано', '16 Янв 2005', 'Хороший', 3, 'Мужской', '5d0a87ed6f86516b7122f791.png', 'Kurt', 88888888, 0, 1, 'Klark', '3 класс '),
(4, 'q@mail.ru', '$2a$10$0LcQSyiTuo9tzJGQAPDheuGpE9aQRCmiANEFUzzp0u2StPuhHuSvq', 'Не указано', '15 Апр 1979', 'Не указано', 1, 'Мужской', 'd464e887-7b98-44aa-8aa7-4abba72abb45.1572887229133728067.jpg', 'Serj', 0, 0, 2, 'Serj', 'Не указано'),
(5, 'w@mail.ru', '$2a$10$u1ISSs/8nutT23kqMdY.tOAgrmG47nD5enRN0Zfxl6hGuAhbOmXA2', 'Не указано', '18 Фев 1916', 'Не указано', 0, 'Мужской', 'avatarDefault.jpg', 'will', 0, 0, 0, 'will', 'Не указано'),
(6, 's@mail.ru', '$2a$10$eLvTEp5MG4oGYCA9tuIO2uCi7Kq9YHjbDWMMOxzJn4PDlOS1PsOMu', 'Не указано', '17 Июн 2007', 'Не указано', 1, 'Мужской', '823c7cc1-3120-4e88-97ee-6289cbfd635d.загруженное.jpg', 'Smith', 0, 0, 1, 'Smith', 'Не указано');

-- --------------------------------------------------------

--
-- Структура таблицы `users_roles`
--

CREATE TABLE `users_roles` (
  `users_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `users_roles`
--

INSERT INTO `users_roles` (`users_id`, `roles_id`) VALUES
(1, 1),
(2, 1),
(5, 1),
(6, 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs1slvnkuemjsq2kj4h3vhx7i1` (`post_id`),
  ADD KEY `FKqm52p1v3o13hy268he0wcngr5` (`user_id`);

--
-- Индексы таблицы `followers`
--
ALTER TABLE `followers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9w6mv39vle9f9yacvvkfieai7` (`follower_id`),
  ADD KEY `FK250x9cowxgy2k0u63h54tfpxv` (`subscriber_id`);

--
-- Индексы таблицы `like_post`
--
ALTER TABLE `like_post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjdv3j0wwk6s2cujoh18p5ko61` (`user_id`),
  ADD KEY `FKnu91sbh82a5nj1o3xh0sgwhu8` (`post_id`);

--
-- Индексы таблицы `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp6qr084ps76jxtu2k924ieudq` (`user1_id`),
  ADD KEY `FKbsodkcoer7puydq6r617a9mn1` (`user2_id`);

--
-- Индексы таблицы `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7ky67sgi7k0ayf22652f7763r` (`user_id`),
  ADD KEY `FKge12ma7mhcguxwoqi5jk534x2` (`like_post_id`),
  ADD KEY `FK4ojy9fux3i0qtfu741fkp1cai` (`comment_id`);

--
-- Индексы таблицы `post_like_post`
--
ALTER TABLE `post_like_post`
  ADD KEY `FK29cjmcujhjyago20auoseh6wa` (`like_post_id`),
  ADD KEY `FKixv1fj1ru2tych3vuwjsgbc6d` (`post_id`);

--
-- Индексы таблицы `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`users_id`,`roles_id`),
  ADD KEY `FKa62j07k5mhgifpp955h37ponj` (`roles_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `comment`
--
ALTER TABLE `comment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT для таблицы `followers`
--
ALTER TABLE `followers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT для таблицы `like_post`
--
ALTER TABLE `like_post`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT для таблицы `message`
--
ALTER TABLE `message`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT для таблицы `post`
--
ALTER TABLE `post`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT для таблицы `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FKqm52p1v3o13hy268he0wcngr5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKs1slvnkuemjsq2kj4h3vhx7i1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);

--
-- Ограничения внешнего ключа таблицы `followers`
--
ALTER TABLE `followers`
  ADD CONSTRAINT `FK250x9cowxgy2k0u63h54tfpxv` FOREIGN KEY (`subscriber_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK9w6mv39vle9f9yacvvkfieai7` FOREIGN KEY (`follower_id`) REFERENCES `users` (`id`);

--
-- Ограничения внешнего ключа таблицы `like_post`
--
ALTER TABLE `like_post`
  ADD CONSTRAINT `FKjdv3j0wwk6s2cujoh18p5ko61` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKnu91sbh82a5nj1o3xh0sgwhu8` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);

--
-- Ограничения внешнего ключа таблицы `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FKbsodkcoer7puydq6r617a9mn1` FOREIGN KEY (`user2_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKp6qr084ps76jxtu2k924ieudq` FOREIGN KEY (`user1_id`) REFERENCES `users` (`id`);

--
-- Ограничения внешнего ключа таблицы `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `FK4ojy9fux3i0qtfu741fkp1cai` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`),
  ADD CONSTRAINT `FK7ky67sgi7k0ayf22652f7763r` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKge12ma7mhcguxwoqi5jk534x2` FOREIGN KEY (`like_post_id`) REFERENCES `like_post` (`id`);

--
-- Ограничения внешнего ключа таблицы `post_like_post`
--
ALTER TABLE `post_like_post`
  ADD CONSTRAINT `FK29cjmcujhjyago20auoseh6wa` FOREIGN KEY (`like_post_id`) REFERENCES `like_post` (`id`),
  ADD CONSTRAINT `FKixv1fj1ru2tych3vuwjsgbc6d` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);

--
-- Ограничения внешнего ключа таблицы `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FKa62j07k5mhgifpp955h37ponj` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKml90kef4w2jy7oxyqv742tsfc` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
