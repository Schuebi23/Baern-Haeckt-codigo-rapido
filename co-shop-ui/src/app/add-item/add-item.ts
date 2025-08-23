import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {debounceTime, distinctUntilChanged , Observable, Subject, takeUntil} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {ItemCreate, Product} from '../group/group';
import {AsyncPipe} from '@angular/common';
import {AddItemDialog} from './add-item-dialog/add-item-dialog.component';
import {AddItemService} from './add-item.service';
import {GroupStore} from '../group/group.store';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-add-item',
  imports: [ReactiveFormsModule, AsyncPipe, AddItemDialog],
  templateUrl: './add-item.html',
  styleUrl: './add-item.css'
})
export class AddItem implements OnInit, OnDestroy {
  httpClient = inject(HttpClient);
  groupStore = inject(GroupStore)
  route = inject(ActivatedRoute);

  searchControl = new FormControl('');
  searchResults$: Observable<Product[]> = this.httpClient.get<Product[]>('http://localhost:8080/products');
  createItem = false;

  service = inject(AddItemService);
  groupId = -1
  destroy$ = new Subject<void>();

  ngOnInit() {
    this.searchControl.valueChanges
      .pipe(
        debounceTime(300),
        distinctUntilChanged(),
        takeUntil(this.destroy$)
      ).subscribe(term => {
      this.searchResults$ = this.httpClient.get<Product[]>('http://localhost:8080/products?name=' + term);
    });

    this.route.params.subscribe((params) => {
      this.groupId = +params['groupId'];
    });
  }

  addItem() {
    this.createItem = true;
  }

  onItemCreated(item: ItemCreate) {
    this.service.createItem(this.groupId, item)
    this.createItem = false;
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
