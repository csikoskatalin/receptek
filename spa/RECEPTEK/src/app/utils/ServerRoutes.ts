
export class Routes {
  static LOGIN: String = 'user/login';
  static USERS:String = 'user/users';
  static REGISTER: String = 'user/register';
  static LOGOUT: String = 'user/logout';
  static RECEPTEK: String = 'recept';
}

export class Server {
  private static address: String = 'localhost';
  private static port: String = '9090';
  private static prefix: String = 'v1';

  static routeTo(route: String) {
    return `http://${Server.address}:${Server.port}/${Server.prefix}/${route}`
  }
}
