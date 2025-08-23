import {Component, output} from '@angular/core';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-group-join-modal',
  templateUrl: './group-join-modal.html',
  styleUrls: ['./group-join-modal.css'],
  imports: [FormsModule]
})
export class GroupJoinModalComponent {
  code: string = '';

  confirm = output<string>()
  cancel = output()

  onConfirm() {
    this.confirm.emit(this.code);
  }

  onCancel() {
    this.cancel.emit();
  }
}
