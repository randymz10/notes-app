import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  notes: any[] = [];
  note = {
    id: null,
    title: '',
    description: '',
    archived: false
  }

  constructor(
    private appService: AppService
  ) { }

  ngOnInit(): void {
    this.getNotes();
  }

  getNotes() {
    this.appService.getNotes()
      .subscribe((data: any) => this.notes = data);
  }

  getNotesArchived(){
    this.appService.getNotesArchived()
    .subscribe((data: any) => this.notes = data);
  }

  saveNote() {
    if (this.note.id) {
      this.appService.updateNote(this.note.id!, this.note)
        .subscribe(() => this.getNotes());
    } else {
      this.appService.createNote(this.note)
        .subscribe(() => this.getNotes());
    }


    this.note = {
      id: null,
      title: '',
      description: '',
      archived: false
    }
  }

  editNote(note: any) {
    this.note = {
      ...note
    };
  }

  deleteNote(note: any){
    this.appService.deleteNote(note.id)
    .subscribe(() => this.getNotes());
  }
}
