import {Component, input, output} from '@angular/core';
import {FormsModule} from '@angular/forms';

type DialogConfig = {
  title: string;
  label: string;
  confirmText: string;
}

@Component({
  selector: 'app-text-modal',
  templateUrl: './text-modal.html',
  styleUrls: ['./text-modal.css'],
  imports: [FormsModule]
})
export class TextModalComponent {
  text: string = '';

  readonly config = input.required<DialogConfig>();

  confirm = output<string>();
  cancel = output();

  onConfirm() {
    this.confirm.emit(this.text);
  }

  onCancel() {
    this.cancel.emit();
  }
}
