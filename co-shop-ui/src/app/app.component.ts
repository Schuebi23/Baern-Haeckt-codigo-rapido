import {ChangeDetectionStrategy, Component} from '@angular/core';
import {RouterModule} from '@angular/router';

@Component({
    imports: [
        RouterModule
    ],
    changeDetection: ChangeDetectionStrategy.OnPush,
    selector: 'app-root',
    template: `
      <router-outlet></router-outlet>
  `
})
export class AppComponent {
}
