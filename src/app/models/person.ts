export class Person {
  id!: number;
  email!: string;
  password!: string;
  role!: Role;

  constructor(email: string, password: string, role: Role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }
}


export enum Role {
  USER='USER',
  ADMIN='ADMIN'
}
