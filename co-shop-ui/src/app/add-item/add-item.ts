import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {debounceTime, distinctUntilChanged, map, Observable, of, Subject, switchMap, takeUntil} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Product} from '../group/group';
import {AsyncPipe} from '@angular/common';

@Component({
  selector: 'app-add-item',
  imports: [ReactiveFormsModule, AsyncPipe],
  templateUrl: './add-item.html',
  styleUrl: './add-item.css'
})
export class AddItem implements OnInit, OnDestroy {
  httpClient = inject(HttpClient);

  searchControl = new FormControl('');
  searchResults$: Observable<Product[]> = this.httpClient.get<Product[]>('http://localhost:8080/products');

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
  }

  addItem(name: string) {

  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
