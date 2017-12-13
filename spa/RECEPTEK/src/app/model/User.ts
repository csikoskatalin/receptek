export class Role {
  static GUEST: String = "GUEST";
  static USER: String = "USER";
  static ADMIN: String = "ADMIN";
  static MODERATOR: String = "MODERATOR"
}

export class User {
  id: number;
  username: String;
  password: String;
  email: String;
  role: String;

  constructor(username?: String, password?: String, email?: String, role?: String) {
    this.username = username || "";
    this.password = password || "";
    this.email = email || "";
    this.role = role || Role.GUEST;
  }
}
