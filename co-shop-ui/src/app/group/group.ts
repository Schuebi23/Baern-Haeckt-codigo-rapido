export type Item = {
  id: number;
  name?: string;
  description?: string;
  unit: string;
  requests: ItemRequest[];
  commits: Commit[];
  product?: Product;
}

export type ItemCreate = {
  name: string;
  productId?: number;
  unit: string;
  description?: string;
  menge: number;
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
  unit: string;
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
