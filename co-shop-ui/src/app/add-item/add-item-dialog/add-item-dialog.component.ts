import {Component, Input, output, signal} from '@angular/core';
import { FormsModule } from '@angular/forms'; // für ngModel

@Component({
  selector: 'app-add-item-dialog',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-item-dialog.html',
  styleUrls: ['./add-item-dialog.css']
})
export class AddItemDialog {
  // Formularwerte als Signal
  form = signal({
    name: '',
    unit: '',
    menge: 0,
    description: ''
  });

  @Input() name: string = 'name'

  confirm = output<any>();
  cancel = output();

  onConfirm() {
    const formValue = this.form();

    this.confirm.emit( {
      name: formValue.name,
      unit: formValue.unit,
      description: formValue.description || undefined,
      productId: 0,
    });
  }

  onCancel() {
    this.cancel.emit();
  }
}
