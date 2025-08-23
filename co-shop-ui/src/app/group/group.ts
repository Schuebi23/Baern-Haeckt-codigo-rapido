export type Item = {
  id: number;
  name?: string;
  description?: string;
  unit: Unit;
  requests: ItemRequest[];
  commits: Commit[];
  product?: Product;
}

export type ItemCreate = {
  name: string;
  productId?: number;
  unit: Unit;
  description?: string;
}

export enum Unit {
  PIECE = 'PIECE',
  KG = 'KG',
  L = 'L'
}

export type ItemRequest = {
  id?: number;
  itemId: number;
  memberId: number;
  qtyRequested: number;
  forEveryone: boolean;
}

export type Commit = {
  id?: number;
  memberId: number;
  itemId: number;
  qtyCommitted: number;
  price?: number;
  commited: boolean;
}

export type Product = {
  id: number;
  name: string;
  unit: Unit;
}

export type Member = {
  id: number;
  displayName: string;
}

export type Group = {
  id: number;
  name: string;
  startDate: Date;
  endDate: Date;
}

export type RequestCommitForDisplay = {
  memberId: number;
  qtyRequested: number;
  qtyCommitted: number;
  forEveryone: boolean;
  price: number;
  commited: boolean;
}
