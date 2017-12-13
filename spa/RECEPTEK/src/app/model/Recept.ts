import {User} from "./User";

export class ReceptStatus {
  static PUBLIC: String = "PUBLIC";
  static PRIVATE: String = "PRIVATE";
  static PENDING: String = "PENDING";
}

export class Recept {
  id: number;
  user: User;
  name: String;
  text: String;
  status: String;
  bevasarlolista: String[];

  constructor(name?: String, text?: String, status?: String, user?: User, id?: number) {
    this.user = user;
    this.name = name;
    this.status = status;
    this.text = text;
    this.id = id;
  }
}
