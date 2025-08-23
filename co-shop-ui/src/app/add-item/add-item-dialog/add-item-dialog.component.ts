import {Component, Input, OnInit, output, signal} from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-item-dialog',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-item-dialog.html',
  styleUrls: ['./add-item-dialog.css']
})
export class AddItemDialog implements OnInit {
  ngOnInit(): void {
      this.form.set({
        name: this.name,
        unit: this.unit,
        menge: 0,
        description: ''
      })
  }
  // Formularwerte als Signal
  form = signal({
    name: '',
    unit: '',
    menge: 0,
    description: ''
  });

  @Input() name: string = "";
  @Input() unit: string = "";

  confirm = output<any>();
  cancel = output();

  onConfirm() {
    const formValue = this.form();

    this.confirm.emit( {
      name: formValue.name,
      unit: formValue.unit,
      description: formValue.description || undefined,
      menge: formValue.menge,
      productId: 0,
    });
  }

  onCancel() {
    this.cancel.emit();
  }
}
