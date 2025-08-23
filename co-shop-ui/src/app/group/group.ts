export type Item = {
  id: number;
  name?: string;
  description?: string;
  unit: Unit;
  requests: ItemRequest[];
  commits: Commit[];
  product?: Product;
}

export enum Unit {
  PIECE = 'PIECE',
  KG = 'KG',
  L = 'L'
}

export type ItemRequest = {
  id?: number;
  memberId: number;
  qtyRequested: number;
  forEveryone: boolean;
}

export type Commit = {
  id?: number;
  memberId: number;
  quantity: number;
  price?: number;
  commited: boolean;
}

export type Product = {
  id: number;
  name: string;
  unit: Unit;
}
