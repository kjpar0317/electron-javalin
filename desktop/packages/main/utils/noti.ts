import { Notification } from 'electron'

export function showNotification (title: string, message: string) {
    new Notification({ title: title, body: message, subtitle: '알림', hasReply: true }).show()
}