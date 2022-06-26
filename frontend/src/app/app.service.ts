import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const API_BASE = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(
    private http: HttpClient
  ) { }

  getNotes(){
    return this.http.get(`${API_BASE}/notes`);
  }

  getNotesArchived(){
    return this.http.get(`${API_BASE}/notes/archived`);
  }

  geOneNote(id: number){
    return this.http.get(`${API_BASE}/notes/${id}`);
  }

  createNote(note: any) {
    return this.http.post(`${API_BASE}/notes`, note);
  }

  updateNote(id: number, note:any){
    return this.http.put(`${API_BASE}/notes/${id}`, note);
  }

  archivedNote(id: number, note:any){
    return this.http.put(`${API_BASE}/notes/archived/${id}`, note);
  }

  deleteNote(id: number){
    return this.http.delete(`${API_BASE}/notes/${id}`);
  }
}
