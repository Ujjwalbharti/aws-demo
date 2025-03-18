CREATE INDEX if not exists idx_users_username ON users(user_name);

CREATE INDEX if not exists idx_queues_queue_name_user_id ON queues(queue_name, user_id);

CREATE INDEX if not exists idx_messages_queue_id_message_id ON messages(queue_id, message_id);

CREATE INDEX if not exists idx_messages_message_id ON messages(message_id);

