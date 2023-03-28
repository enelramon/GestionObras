using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using GestionObras.Api.DAL;
using GestionObras.Api.Models;

namespace GestionObras.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AdelantosController : ControllerBase
    {
        private readonly Contexto _context;

        public AdelantosController(Contexto context)
        {
            _context = context;
        }

        // GET: api/Adelantos
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Adelantos>>> GetAdelantos()
        {
            if (_context.Adelantos == null)
            {
                return NotFound();
            }
            return await _context.Adelantos.ToListAsync();
        }

        // GET: api/Adelantos/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Adelantos>> GetAdelantos(int id)
        {
            if (_context.Adelantos == null)
            {
                return NotFound();
            }
            var adelantos = await _context.Adelantos.FindAsync(id);

            if (adelantos == null)
            {
                return NotFound();
            }

            return adelantos;
        }

        // PUT: api/Adelantos/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutAdelantos(int id, Adelantos adelantos)
        {
            if (id != adelantos.AdelantoId)
            {
                return BadRequest();
            }

            _context.Entry(adelantos).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AdelantosExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Adelantos
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Adelantos>> PostAdelantos(Adelantos adelantos)
        {
            if (_context.Adelantos == null)
            {
                return Problem("Entity set 'Contexto.Adelantos'  is null.");
            }
            _context.Adelantos.Add(adelantos);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetAdelantos", new { id = adelantos.AdelantoId }, adelantos);
        }

        // DELETE: api/Adelantos/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteAdelantos(int id)
        {
            if (_context.Adelantos == null)
            {
                return NotFound();
            }
            var adelantos = await _context.Adelantos.FindAsync(id);
            if (adelantos == null)
            {
                return NotFound();
            }

            _context.Adelantos.Remove(adelantos);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool AdelantosExists(int id)
        {
            return (_context.Adelantos?.Any(a => a.AdelantoId == id)).GetValueOrDefault();
        }
    }
}
